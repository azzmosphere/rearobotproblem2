package au.azzmosphere.worlds;

import au.azzmosphere.physicalobject.PhysicalObject;

public interface World {

    void placePhysicalObject(PhysicalObject physicalObject, int x, int y) throws CoordinateOutOfBoundsException, OccupiedByAnotherObjectException;
    void movePhysicalObject(PhysicalObject physicalObject, int x, int y) throws OccupiedByAnotherObjectException, ObjectNotYetPlacedException, CoordinateOutOfBoundsException;
    void removePhysicalObject(PhysicalObject physicalObject) throws ObjectNotYetPlacedException;
    PhysicalObject getPhysicalObject(int x, int y);
    boolean[][] getVertices();
    int[] getEdges(PhysicalObject physicalObject);
}
