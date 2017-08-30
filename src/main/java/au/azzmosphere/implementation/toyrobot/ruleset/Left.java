package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import au.azzmosphere.worlds.InvalidMovementException;
import au.azzmosphere.worlds.ObjectNotYetPlacedException;
import au.azzmosphere.worlds.OccupiedByAnotherObjectException;

public final class Left extends AbstractRule {
    @Override
    protected boolean performAction(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException {
        boolean result = true;



        return result;
    }
}
