package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.requests.Request;
import au.azzmosphere.requests.RequestType;
import au.azzmosphere.worlds.RulesetCannotBeFoundException;
import au.azzmosphere.worlds.World;
import au.azzmosphere.worlds.WorldRuleSetFactory;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestWorldRuleSetFactory {
    private World world = mock(World.class);
    private WorldRuleSetFactory worldRuleSetFactory = new RobotRuleSetFactory();

    @Before
    public void setup() {
        worldRuleSetFactory.setWorld(world);
    }

    @Test
    public void testLeft() throws Exception {
        Request request = mock(Request.class);
        when(request.getType()).thenReturn(RequestType.LEFT);
        assertThat(worldRuleSetFactory.getRuleSet(request), is(Left.class));
    }

    @Test
    public void testRight() throws Exception {
        Request request = mock(Request.class);
        when(request.getType()).thenReturn(RequestType.RIGHT);
        assertThat(worldRuleSetFactory.getRuleSet(request), is(Right.class));
    }

    @Test
    public void testMove() throws Exception {
        Request request = mock(Request.class);
        when(request.getType()).thenReturn(RequestType.MOVE);
        assertThat(worldRuleSetFactory.getRuleSet(request), is(Move.class));
    }

    @Test
    public void testReport() throws Exception {
        Request request = mock(Request.class);
        when(request.getType()).thenReturn(RequestType.REPORT);
        assertThat(worldRuleSetFactory.getRuleSet(request), is(Report.class));
    }

    @Test(expected = RulesetCannotBeFoundException.class)
    public void testNull() throws Exception {
        Request request = mock(Request.class);
        worldRuleSetFactory.getRuleSet(request);
    }

}
