package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.physicalobject.perspective.CardinalDirection;
import au.azzmosphere.physicalobject.perspective.Perspective;
import au.azzmosphere.physicalobject.perspective.PerspectiveImpl;
import au.azzmosphere.requests.Request;
import au.azzmosphere.requests.RequestImp;
import au.azzmosphere.worlds.World;
import au.azzmosphere.worlds.WorldRuleSet;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestPlace {
    private WorldRuleSet place = new Place();
    private World world = mock(World.class);

    @Before
    public void setup() {
        place.setWorld(world);
    }

    @Test
    public void testPlaceNorth() throws Exception {
        Request placeRequest = new RequestImp();
        Perspective perspective = new PerspectiveImpl();

        placeRequest.setParameter("xpos", "0");
        placeRequest.setParameter("ypos", "0");
        placeRequest.setParameter("perspective", "NORTH");

        assertThat(place.run(placeRequest), is(true));

        verify(world).placePhysicalObject(any(PhysicalObject.class), eq(0), eq(0));
    }
}
