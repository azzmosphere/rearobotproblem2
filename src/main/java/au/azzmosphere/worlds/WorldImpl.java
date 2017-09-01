package au.azzmosphere.worlds;

import au.azzmosphere.physicalobject.PhysicalObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WorldImpl implements World {

    private int width, height;
    private boolean[][] vertices;
    private Map<PhysicalObject, int[]> objectMap = new HashMap<>();

    public WorldImpl(int width, int height) {
        this.width = width;
        this.height = height;
        vertices = new boolean[width][height];
    }

    private boolean canPlaceObject(int x, int y) throws CoordinateOutOfBoundsException, OccupiedByAnotherObjectException {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new CoordinateOutOfBoundsException("coordinate x and y are out of bounds");
        }
        else if (vertices[x][y]) {
            throw new OccupiedByAnotherObjectException("vertices all ready has a physical object in place");
        }

        return true;
    }

    @Override
    public void placePhysicalObject(PhysicalObject physicalObject, int x, int y) throws CoordinateOutOfBoundsException, OccupiedByAnotherObjectException {
        if (canPlaceObject(x, y)) {
            vertices[x][y] = true;
            objectMap.put(physicalObject, new int[]{x, y});
        }
    }

    @Override
    public void movePhysicalObject(PhysicalObject physicalObject, int x, int y) throws OccupiedByAnotherObjectException, ObjectNotYetPlacedException, CoordinateOutOfBoundsException {
        if (!objectMap.containsKey(physicalObject)) {
            throw new ObjectNotYetPlacedException("object must be placed before it can be placed");
        }

        if (canPlaceObject(x, y)) {
            int[] coordinates = objectMap.get(physicalObject);
            vertices[coordinates[0]][coordinates[1]] = false;
            vertices[x][y] = true;
            objectMap.put(physicalObject, new int[] {x, y});
        }
    }

    @Override
    public void removePhysicalObject(PhysicalObject physicalObject) throws ObjectNotYetPlacedException {
        if (!objectMap.containsKey(physicalObject)) {
            throw new ObjectNotYetPlacedException("a object must be part of the world before it can be deleted");
        }
        int[] coordinates = objectMap.get(physicalObject);
        vertices[coordinates[0]][coordinates[1]] = false;
        objectMap.remove(physicalObject);

    }

    @Override
    public PhysicalObject getPhysicalObject(int x, int y) {
        Iterator it = objectMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            int[] coordinates = (int[]) pair.getValue();

            if (coordinates[0] == x && coordinates[1] == y) {
                return (PhysicalObject) pair.getKey();
            }
        }

        return null;
    }

    @Override
    public boolean[][] getVertices() {
        return vertices;
    }

    @Override
    public int[] getEdges(PhysicalObject physicalObject) {
        return objectMap.get(physicalObject);
    }
}
