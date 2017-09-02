package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.physicalobject.PhysicalObject;
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

        // Be explicit about X and Y or we could be removing the wrong
        // object if one all ready exists.
        int x = Integer.parseInt((String) request.getParameter("xpos"));
        int y = Integer.parseInt((String) request.getParameter("ypos"));
        PhysicalObject physicalObject = world.getPhysicalObject(x, y);

        world.removePhysicalObject(physicalObject);
        request.setPhysicalObject(getPhysicalObject());

        return result;
    }

    /*
     * A little hacky but because AJAX controllers won't predict
     * when a call is made (what sequence) if there is a physical
     * object retrieve it from the world otherwise the
     * one that has been placed won't work.
     */
    private PhysicalObject getPhysicalObject() {
        boolean[][] vertices = world.getVertices();
        PhysicalObject physicalObject = null;

        for (int y = 0; y < vertices.length; y++) {
            for (int x = 0; x < vertices.length; x++) {
                if (vertices[x][y]) {
                    physicalObject = world.getPhysicalObject(x, y);
                    break;
                }
            }
        }

        return physicalObject;
    }
}
