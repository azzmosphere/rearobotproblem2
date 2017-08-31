package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.physicalobject.perspective.CardinalDirection;
import au.azzmosphere.physicalobject.perspective.Perspective;
import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import au.azzmosphere.worlds.InvalidMovementException;
import au.azzmosphere.worlds.ObjectNotYetPlacedException;
import au.azzmosphere.worlds.OccupiedByAnotherObjectException;

public class Right extends AbstractRule {
    @Override
    protected boolean performAction(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException {
        boolean result = true;

        Perspective perspective = request.getPhysicalObject().getPerspective();

        switch (perspective.getDirection()) {
            case NORTH:
                perspective.setDirection(CardinalDirection.WEST);
                break;
            case EAST:
                perspective.setDirection(CardinalDirection.NORTH);
                break;
            case SOUTH:
                perspective.setDirection(CardinalDirection.EAST);
                break;
            case WEST:
                perspective.setDirection(CardinalDirection.SOUTH);
        }

        return result;
    }
}