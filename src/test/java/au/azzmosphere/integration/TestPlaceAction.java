package au.azzmosphere.integration;

import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.physicalobject.perspective.CardinalDirection;
import au.azzmosphere.requests.Request;
import au.azzmosphere.requests.RequestImp;
import au.azzmosphere.requests.RequestType;
import au.azzmosphere.responses.Response;
import au.azzmosphere.responses.ResponseStatus;
import au.azzmosphere.services.ActionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Performs a integration test from the service onwards. In this test the controller is
 * not touched.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
@ContextConfiguration(locations = {"/test-config.xml"})
public class TestPlaceAction {

    @Autowired
    private ActionService actionService;
    private Request request = new RequestImp();

    @Before
    public void performAction() throws Exception {
        request.setType(RequestType.PLACE);
        request.setParameter("xpos", "0");
        request.setParameter("ypos", "0");
        request.setParameter("perspective", "NORTH");

        actionService.run(request);
    }


    @Test
    public void testResult() throws Exception {
        assertThat(request.getResponse().getStatus(), is(ResponseStatus.SUCCESS));
        assertThat(request.getResponse(), instanceOf(Response.class));
        assertThat(request.getPhysicalObject(), instanceOf(PhysicalObject.class));
        assertThat(request.getPhysicalObject().getPerspective().getDirection(), is(CardinalDirection.NORTH));
    }
}
