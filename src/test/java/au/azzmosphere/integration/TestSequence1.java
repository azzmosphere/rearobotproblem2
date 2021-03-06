package au.azzmosphere.integration;


import au.azzmosphere.requests.RequestType;
import au.azzmosphere.responses.Response;
import au.azzmosphere.responses.ResponseStatus;
import au.azzmosphere.services.ActionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static au.azzmosphere.integration.GenericRequestTester.performAction;
import static au.azzmosphere.integration.GenericRequestTester.performPlace;

/**
 * Peforms the following seqeuence
 * PLACE 0,0,NORTH
 * MOVE
 * REPORT
 * Expected output:
 *
 * 0,1,NORTH
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
@ContextConfiguration(locations = {"/test-config.xml"})
public class TestSequence1 {
    @Autowired
    private ActionService actionService;

    @Test
    public void testResult() throws Exception {
        performPlace(actionService, "0", "0", "NORTH");
        performAction(actionService, RequestType.MOVE);
        Response response = performAction(actionService, RequestType.REPORT);

        assertThat(response.getStatus(), is(ResponseStatus.SUCCESS));
        assertThat(response.getParameter("xpos"), is(0));
        assertThat(response.getParameter("ypos"), is(1));
        assertThat(response.getParameter("message"), is("REPORT"));
        assertThat(response.getParameter("direction"), is("NORTH"));
    }
}
