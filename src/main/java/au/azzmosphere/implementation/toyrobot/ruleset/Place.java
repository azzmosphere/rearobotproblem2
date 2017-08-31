package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.implementation.toyrobot.phyiscalobject.ToyRobot;
import au.azzmosphere.physicalobject.perspective.CardinalDirection;
import au.azzmosphere.physicalobject.perspective.Perspective;
import au.azzmosphere.physicalobject.perspective.PerspectiveImpl;
import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.WorldRuleSet;
import au.azzmosphere.worlds.OccupiedByAnotherObjectException;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import org.apache.log4j.Logger;

public final class Place extends AbstractRule implements WorldRuleSet {
    private static final Logger logger = Logger.getLogger(WorldRuleSet.class);

    @Override
    protected final boolean performAction(Request request) throws CoordinateOutOfBoundsException, OccupiedByAnotherObjectException {

        logger.debug("performing place action");
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
}
