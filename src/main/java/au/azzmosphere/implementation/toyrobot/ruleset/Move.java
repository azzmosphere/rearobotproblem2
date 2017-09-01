package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.WorldRuleSet;
import au.azzmosphere.worlds.ObjectNotYetPlacedException;
import au.azzmosphere.worlds.OccupiedByAnotherObjectException;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import au.azzmosphere.worlds.InvalidMovementException;

/**
 * moves physical object one unit.
 */

public final class Move extends AbstractRule implements WorldRuleSet {

    @Override
    protected boolean performAction(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException {
        boolean result = true;
        PhysicalObject physicalObject = request.getPhysicalObject();
        int[] edges = world.getEdges(physicalObject);
        int x = edges[0], y = edges[1];

        switch (physicalObject.getPerspective().getDirection()) {
            case NORTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
            case SOUTH:
                y--;
                break;
            default:
                throw new InvalidMovementException("unable to determine which way to move physical object");
        }
        world.movePhysicalObject(physicalObject, x, y);

        return result;
    }
}
