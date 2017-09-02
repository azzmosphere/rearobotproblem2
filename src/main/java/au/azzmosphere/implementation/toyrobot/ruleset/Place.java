package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.implementation.toyrobot.phyiscalobject.ToyRobot;
import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.physicalobject.perspective.CardinalDirection;
import au.azzmosphere.physicalobject.perspective.Perspective;
import au.azzmosphere.physicalobject.perspective.PerspectiveImpl;
import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.ObjectNotYetPlacedException;
import au.azzmosphere.worlds.WorldRuleSet;
import au.azzmosphere.worlds.OccupiedByAnotherObjectException;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import org.apache.log4j.Logger;

public final class Place extends AbstractRule implements WorldRuleSet {
    private static final Logger logger = Logger.getLogger(WorldRuleSet.class);

    @Override
    protected boolean performAction(Request request) throws CoordinateOutOfBoundsException, OccupiedByAnotherObjectException {

        logger.debug("performing place action");

        try {
            PhysicalObject physicalObject = getPhysicalObject();
            if (physicalObject != null) {
                world.removePhysicalObject(physicalObject);
            }
        }
        catch (ObjectNotYetPlacedException e) {
            logger.warn("attempting to remove a object that has not been placed");
        }

        int x, y;
        request.setPhysicalObject(new ToyRobot());
        boolean result = true;
        x = Integer.parseInt((String) request.getParameter("xpos"));
        y = Integer.parseInt((String) request.getParameter("ypos"));

        Perspective perspective = new PerspectiveImpl();

        for (CardinalDirection c : CardinalDirection.values()) {
            if (c.toString().equals(request.getParameter("perspective"))) {
                perspective.setDirection(c);
                break;
            }
        }

        request.getPhysicalObject().setPerspective(perspective);
        world.placePhysicalObject(request.getPhysicalObject(), x, y);

        return result;
    }

    /*
     *  This is hacky, but because AJAX is synchronise and
     *  the requirements state that we do not specify which
     *  object is to be moved, etc. We need a way to delete
     *  any objects that are currently existing.
     *
     *  There will only be one so find it, remove it.
     *
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
