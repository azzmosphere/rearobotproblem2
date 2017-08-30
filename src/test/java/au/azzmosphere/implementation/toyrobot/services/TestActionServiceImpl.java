package au.azzmosphere.implementation.toyrobot.services;

import au.azzmosphere.requests.Request;
import au.azzmosphere.responses.Response;
import au.azzmosphere.responses.ResponseStatus;
import au.azzmosphere.services.ActionService;
import au.azzmosphere.worlds.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestActionServiceImpl {
    private ActionService actionService = new ActionServiceImpl();
    private WorldRuleSetFactory worldRuleSetFactory = mock(WorldRuleSetFactory.class);


    @Before
    public void setup() {
        actionService.setWorldRulesetFactory(worldRuleSetFactory);
    }

    @Test
    public void testCoordinateOutOfBounds() throws Exception {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        WorldRuleSet ruleSet = mock(WorldRuleSet.class);
        when(request.getResponse()).thenReturn(response);
        when(worldRuleSetFactory.getRuleSet(request)).thenReturn(ruleSet);
        when(ruleSet.run(request)).thenThrow(new CoordinateOutOfBoundsException("test"));

        actionService.run(request);
        verify(response, atLeastOnce()).setStatus(ResponseStatus.EXCEPTION);
    }

    @Test
    public void testInvalidMovement() throws Exception {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        WorldRuleSet ruleSet = mock(WorldRuleSet.class);
        when(request.getResponse()).thenReturn(response);
        when(worldRuleSetFactory.getRuleSet(request)).thenReturn(ruleSet);
        when(ruleSet.run(request)).thenThrow(new InvalidMovementException("test"));

        actionService.run(request);
        verify(response, atLeastOnce()).setStatus(ResponseStatus.INVALID);
    }

    @Test
    public void testSuccess() throws Exception {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        WorldRuleSet ruleSet = mock(WorldRuleSet.class);
        when(request.getResponse()).thenReturn(response);
        when(worldRuleSetFactory.getRuleSet(request)).thenReturn(ruleSet);
        when(ruleSet.run(request)).thenReturn(true);

        actionService.run(request);
        verify(response, atLeastOnce()).setStatus(ResponseStatus.SUCCESS);
    }

    @Test
    public void testFail() throws Exception {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        WorldRuleSet ruleSet = mock(WorldRuleSet.class);
        when(request.getResponse()).thenReturn(response);
        when(worldRuleSetFactory.getRuleSet(request)).thenReturn(ruleSet);
        when(ruleSet.run(request)).thenReturn(false);

        actionService.run(request);
        verify(response, atLeastOnce()).setStatus(ResponseStatus.FAIL);
    }
}
