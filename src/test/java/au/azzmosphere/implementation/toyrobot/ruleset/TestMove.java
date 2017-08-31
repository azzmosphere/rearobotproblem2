package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.physicalobject.perspective.CardinalDirection;
import au.azzmosphere.physicalobject.perspective.Perspective;
import au.azzmosphere.physicalobject.perspective.PerspectiveImpl;
import au.azzmosphere.requests.Request;
import au.azzmosphere.requests.RequestImp;
import au.azzmosphere.requests.RequestType;
import au.azzmosphere.worlds.World;
import au.azzmosphere.worlds.WorldRuleSet;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

public class TestMove {
    private WorldRuleSet move = new Move();
    private World world = mock(World.class);
    private PhysicalObject physicalObject = mock(PhysicalObject.class);

    @Before
    public void setup() {
        move.setWorld(world);
    }

    @Test
    public void testMoveNorth() throws Exception {
        Request moveRequest = new RequestImp();
        moveRequest.setPhysicalObject(physicalObject);
        Perspective perspective = new PerspectiveImpl();
        perspective.setDirection(CardinalDirection.NORTH);

        when(world.getEdges(physicalObject)).thenReturn(new int[] {2, 2});
        when(physicalObject.getPerspective()).thenReturn(perspective);
        assertThat(move.run(moveRequest), is(true));

        verify(world).movePhysicalObject(any(PhysicalObject.class), eq(2), eq(3));
    }

    @Test
    public void testMoveEast() throws Exception {
        Request moveRequest = new RequestImp();
        moveRequest.setType(RequestType.MOVE);
        moveRequest.setPhysicalObject(physicalObject);
        Perspective perspective = new PerspectiveImpl();
        perspective.setDirection(CardinalDirection.EAST);

        when(world.getEdges(physicalObject)).thenReturn(new int[] {2, 2});
        when(physicalObject.getPerspective()).thenReturn(perspective);
        assertThat(move.run(moveRequest), is(true));

        verify(world).movePhysicalObject(any(PhysicalObject.class), eq(3), eq(2));
    }

    @Test
    public void testMoveSouth() throws Exception {
        Request moveRequest = new RequestImp();
        moveRequest.setType(RequestType.MOVE);
        moveRequest.setPhysicalObject(physicalObject);
        Perspective perspective = new PerspectiveImpl();
        perspective.setDirection(CardinalDirection.SOUTH);

        when(world.getEdges(physicalObject)).thenReturn(new int[] {2, 2});
        when(physicalObject.getPerspective()).thenReturn(perspective);
        assertThat(move.run(moveRequest), is(true));

        verify(world).movePhysicalObject(any(PhysicalObject.class), eq(2), eq(1));
    }

    @Test
    public void testMoveWest() throws Exception {
        Request moveRequest = new RequestImp();
        moveRequest.setType(RequestType.MOVE);
        moveRequest.setPhysicalObject(physicalObject);
        Perspective perspective = new PerspectiveImpl();
        perspective.setDirection(CardinalDirection.WEST);

        when(world.getEdges(physicalObject)).thenReturn(new int[] {2, 2});
        when(physicalObject.getPerspective()).thenReturn(perspective);
        assertThat(move.run(moveRequest), is(true));

        verify(world).movePhysicalObject(any(PhysicalObject.class), eq(1), eq(2));
    }
}
