package au.azzmosphere.physicalobject.perspective;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestPerspectiveImpl {
    private Perspective perspective = new PerspectiveImpl();

    @Test
    public void testSetPerspective() {
        perspective.setDirection(CardinalDirection.EAST);
        assertThat(perspective.getDirection(), CoreMatchers.is(CardinalDirection.EAST));
    }
}
