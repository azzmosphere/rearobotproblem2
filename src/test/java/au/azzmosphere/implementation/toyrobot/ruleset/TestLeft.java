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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestLeft {
    private World world = mock(World.class);
    private WorldRuleSet left = new Left();
    private PhysicalObject physicalObject = mock(PhysicalObject.class);
    private Perspective perspective = new PerspectiveImpl();
    private Request leftRequest = new RequestImp();

    @Before
    public void setup() {
        left.setWorld(world);
        when(physicalObject.getPerspective()).thenReturn(perspective);
        leftRequest.setPhysicalObject(physicalObject);
        leftRequest.setType(RequestType.LEFT);
    }

    @Test
    public void turnFromNorth() throws Exception {
        perspective.setDirection(CardinalDirection.NORTH);
        left.run(leftRequest);

        assertThat(perspective.getDirection(), is(CardinalDirection.EAST));
    }

    @Test
    public void turnFromEast() throws Exception {
        perspective.setDirection(CardinalDirection.EAST);
        left.run(leftRequest);

        assertThat(perspective.getDirection(), is(CardinalDirection.SOUTH));
    }

    @Test
    public void turnFromSouth() throws Exception {
        perspective.setDirection(CardinalDirection.SOUTH);
        left.run(leftRequest);

        assertThat(perspective.getDirection(), is(CardinalDirection.WEST));
    }

    @Test
    public void turnFromWest() throws Exception {
        perspective.setDirection(CardinalDirection.WEST);
        left.run(leftRequest);

        assertThat(perspective.getDirection(), is(CardinalDirection.NORTH));
    }
}
