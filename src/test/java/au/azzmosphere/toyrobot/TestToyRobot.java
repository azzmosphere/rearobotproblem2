package au.azzmosphere.toyrobot;

import au.azzmosphere.implementation.toyrobot.phyiscalobject.ToyRobot;
import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.physicalobject.perspective.Perspective;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestToyRobot {
    private PhysicalObject toyRobot = new ToyRobot();
    private Perspective perspective = mock(Perspective.class);
    private Map<String, Object> attributes = mock(Map.class);

    @Test
    public void testSetPerspective() {
        toyRobot.setPerspective(perspective);
        assertThat(toyRobot.getPerspective(), is(perspective));
    }

    @Test
    public void testSetAttributes() {
        toyRobot.setAttributes(attributes);
        assertThat(toyRobot.getAttributes(), is(attributes));
    }
}
