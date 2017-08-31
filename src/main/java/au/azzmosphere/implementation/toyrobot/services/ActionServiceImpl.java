package au.azzmosphere.implementation.toyrobot.services;


import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.requests.Request;
import au.azzmosphere.responses.ResponseImpl;
import au.azzmosphere.responses.ResponseStatus;
import au.azzmosphere.services.ActionService;
import au.azzmosphere.worlds.WorldRuleSet;
import au.azzmosphere.worlds.WorldRuleSetFactory;
import au.azzmosphere.worlds.RulesetCannotBeFoundException;
import au.azzmosphere.worlds.InvalidMovementException;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class ActionServiceImpl implements ActionService {
    private WorldRuleSetFactory worldRuleSetFactory;
    private static final Logger logger = Logger.getLogger(ActionService.class);
    private PhysicalObject physicalObject;

    @Override
    public final void run(Request request) throws RulesetCannotBeFoundException {
        try {
            logger.debug("attempting to execute request " + request.toString());
            request.setReponse(new ResponseImpl());
            WorldRuleSet worldRuleSet = worldRuleSetFactory.getRuleSet(request);
            request.setPhysicalObject(physicalObject);

            if (worldRuleSet.run(request)) {
                logger.debug("rule-set has returned true");
                request.getResponse().setStatus(ResponseStatus.SUCCESS);

                logger.debug("getting physical object from request.");
                physicalObject = request.getPhysicalObject();

                request.getResponse().setParameterIfNull("message", "success");
            }
            else {
                request.getResponse().setStatus(ResponseStatus.FAIL);
                request.getResponse().setParameterIfNull("message", "fail");
            }
        }
        catch (CoordinateOutOfBoundsException e) {
            logger.error("coordinates are out of bounds - ignoring request");
            request.setParameter("message", "coordinates are out of bounds - ignoring request");
            request.getResponse().setStatus(ResponseStatus.EXCEPTION);
        }
        catch (InvalidMovementException e) {
            logger.error("Physical object can not perform this move - ignoring request");
            request.setParameter("message", "Physical object can not perform this move - ignoring request");
            request.getResponse().setStatus(ResponseStatus.INVALID);
        }
        catch (RulesetCannotBeFoundException e) {
            logger.fatal("unable to handle unknown request - ignoring");
            request.setParameter("message", "unable to handle unknown request - ignoring");
            throw e;
        }
        catch(Exception e) {
            throw new RulesetCannotBeFoundException("could not find a rule set for request " + request.toString());
        }
    }

    @Autowired
    @Override
    public final void setWorldRulesetFactory(WorldRuleSetFactory worldRulesetFactory) {
        this.worldRuleSetFactory = worldRulesetFactory;
    }
}