package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.requests.Request;
import au.azzmosphere.responses.ResponseStatus;
import au.azzmosphere.worlds.*;
import org.apache.log4j.Logger;

public abstract class AbstractRule implements WorldRuleSet {

    protected World world;
    private static final Logger logger = Logger.getLogger(WorldRuleSet.class);

    @Override
    public final void setWorld(World world) {
        this.world = world;
    }


    @Override
    public final boolean run(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException {
        boolean result;

        try {
            result = performAction(request);
        }
        catch (java.lang.NullPointerException e) {
            logger.error("required parameter for action is not defined - ignore action");
            request.getResponse().setParameter("reason", "required parameter for action is not defined - ignore action");
            request.getResponse().setStatus(ResponseStatus.FAIL);
            result = false;
        }

        catch (java.lang.ClassCastException e) {
            logger.error("parameter is not in the correct format - ignore action");
            request.getResponse().setParameter("reason", "parameter is not in the correct format - ignore action");
            request.getResponse().setStatus(ResponseStatus.FAIL);
            result = false;
        }

        return result;
    }

    protected abstract boolean performAction(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException;
}
