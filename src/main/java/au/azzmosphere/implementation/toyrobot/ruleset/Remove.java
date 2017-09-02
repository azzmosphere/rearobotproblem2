package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.WorldRuleSet;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import au.azzmosphere.worlds.InvalidMovementException;
import au.azzmosphere.worlds.OccupiedByAnotherObjectException;
import au.azzmosphere.worlds.ObjectNotYetPlacedException;
import org.apache.log4j.Logger;

public final class Remove extends AbstractRule implements WorldRuleSet {
    private final Logger logger = Logger.getLogger(Remove.class);

    @Override
    protected boolean performAction(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException {
        logger.debug("removing robot action");
        request.getResponse().setParameter("message", "REMOVE");
        boolean result = true;

        world.removePhysicalObject(request.getPhysicalObject());

        request.setPhysicalObject(null);

        return result;
    }
}
