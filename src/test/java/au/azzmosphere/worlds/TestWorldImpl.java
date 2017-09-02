package au.azzmosphere.worlds;

import au.azzmosphere.physicalobject.PhysicalObject;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class TestWorldImpl {
    private World world;

    @Before
    public void setup() {
        world =new WorldImpl(5, 5);
    }

    @Test
    public void testPlaceObject() throws Exception {
        PhysicalObject physicalObject = mock(PhysicalObject.class);
        world.placePhysicalObject(physicalObject, 0, 0);

        assertThat(world.getPhysicalObject(0, 0), is(physicalObject));
    }

    @Test
    public void testMoveObject() throws Exception {
        PhysicalObject physicalObject = mock(PhysicalObject.class);
        world.placePhysicalObject(physicalObject, 0, 0);
        world.movePhysicalObject(physicalObject, 1, 0);

        assertThat(world.getPhysicalObject(1, 0), is(physicalObject));
    }

    @Test
    public void testRemoveObject() throws Exception {
        PhysicalObject physicalObject = mock(PhysicalObject.class);
        world.placePhysicalObject(physicalObject, 0, 0);
        world.removePhysicalObject(physicalObject);

        assertThat(world.getPhysicalObject(0, 0), nullValue());
    }

    @Test
    public void testGetVertices() throws Exception {
        PhysicalObject physicalObject = mock(PhysicalObject.class);
        world.placePhysicalObject(physicalObject, 1, 2);
        int[] edges = world.getEdges(physicalObject);

        assertThat(edges, is(new int[] {1, 2}));
    }
    @Test
    public void testGetVertices2() throws Exception {
        PhysicalObject physicalObject = mock(PhysicalObject.class);
        world.placePhysicalObject(physicalObject, 1, 2);
        world.removePhysicalObject(physicalObject);

        world.placePhysicalObject(physicalObject, 1, 2);

        int[] edges = world.getEdges(physicalObject);
        assertThat(edges, is(new int[] {1, 2}));
    }

    @Test
    public void testMove2() throws Exception {
        PhysicalObject physicalObject = mock(PhysicalObject.class);
        world.placePhysicalObject(physicalObject, 1, 2);
        world.removePhysicalObject(physicalObject);

        world.placePhysicalObject(physicalObject, 1, 2);
        world.movePhysicalObject(physicalObject, 1, 3);

        int[] edges = world.getEdges(physicalObject);
        assertThat(edges, is(new int[] {1, 3}));
    }

    @Test
    public void testGrid() throws Exception {
        PhysicalObject physicalObject = mock(PhysicalObject.class);
        world.placePhysicalObject(physicalObject, 1, 2);
        world.removePhysicalObject(physicalObject);

        world.placePhysicalObject(physicalObject, 1, 2);
        world.movePhysicalObject(physicalObject, 1, 3);

        boolean[][] vertices = world.getVertices();

        System.out.println(vertices.toString());
    }
}
