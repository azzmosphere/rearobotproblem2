package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.CoordinateOutOfBoundsException;
import au.azzmosphere.worlds.InvalidMovementException;
import au.azzmosphere.worlds.ObjectNotYetPlacedException;
import au.azzmosphere.worlds.OccupiedByAnotherObjectException;

public class Report extends AbstractRule {
    @Override
    protected boolean performAction(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException {
        boolean result = true;
        int[] edges = world.getEdges(request.getPhysicalObject());

        request.getResponse().setParameter("xpos", edges[0]);
        request.getResponse().setParameter("ypos", edges[1]);
        request.getResponse().setParameter("direction", request.getPhysicalObject().getPerspective().getDirection().toString());
        request.getResponse().setParameter("message", "REPORT");

        return result;
    }
}
