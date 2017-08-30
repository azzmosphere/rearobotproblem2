package au.azzmosphere.worlds;

import au.azzmosphere.requests.Request;

public interface WorldRuleSet {
    void setWorld(World world);

    boolean run(Request request) throws CoordinateOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException, ObjectNotYetPlacedException;
}
