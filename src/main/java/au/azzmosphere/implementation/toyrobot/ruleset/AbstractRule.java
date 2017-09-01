package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.requests.Request;
import au.azzmosphere.responses.ResponseStatus;
import au.azzmosphere.worlds.InvalidMovementException;
import au.azzmosphere.worlds.World;
import au.azzmosphere.worlds.WorldRuleSet;
import org.apache.log4j.Logger;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import au.azzmosphere.worlds.OccupiedByAnotherObjectException;
import au.azzmosphere.worlds.ObjectNotYetPlacedException;

/**
 * applies a standard work flow that rule sets can follow.
 *
 * exceptions are caught and resolved to messages within the response.
 */

public abstract class AbstractRule implements WorldRuleSet {

    protected World world;
    private static final Logger logger = Logger.getLogger(WorldRuleSet.class);

    /**
     * The world is set by the service and is available
     * to the sub class using the protected world variable.
     *
     * @param world
     */
    @Override
    public final void setWorld(World world) {
        this.world = world;
    }


    /**
     * Called by the service.
     *
     * @param request
     * @return
     * @throws CoordinateOutOfBoundsException
     * @throws InvalidMovementException
     * @throws OccupiedByAnotherObjectException
     * @throws ObjectNotYetPlacedException
     */
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

    /**
     * Sub classes must implement this method which is where the business
     * logic is performed.
     *
     * @param request
     * @return
     * @throws CoordinateOutOfBoundsException
     * @throws InvalidMovementException
     * @throws OccupiedByAnotherObjectException
     * @throws ObjectNotYetPlacedException
     */
    protected abstract boolean performAction(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException;
}
