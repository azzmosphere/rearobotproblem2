package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.physicalobject.perspective.CardinalDirection;
import au.azzmosphere.physicalobject.perspective.Perspective;
import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import au.azzmosphere.worlds.InvalidMovementException;
import au.azzmosphere.worlds.ObjectNotYetPlacedException;
import au.azzmosphere.worlds.OccupiedByAnotherObjectException;

public final class Right extends AbstractRule {
    @Override
    protected boolean performAction(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException {
        boolean result = true;
        Perspective perspective = request.getPhysicalObject().getPerspective();

        switch (perspective.getDirection()) {
            case NORTH:
                perspective.setDirection(CardinalDirection.EAST);
                break;
            case EAST:
                perspective.setDirection(CardinalDirection.SOUTH);
                break;
            case SOUTH:
                perspective.setDirection(CardinalDirection.WEST);
                break;
            case WEST:
                perspective.setDirection(CardinalDirection.NORTH);
                break;
            default:
                throw new InvalidMovementException("unknown perspective");
        }

        request.getResponse().setParameter("message", "RIGHT");

        return result;
    }
}
