package au.azzmosphere.response;

import au.azzmosphere.responses.Response;
import au.azzmosphere.responses.ResponseImpl;
import au.azzmosphere.responses.ResponseStatus;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestResponse {
    private Response response = new ResponseImpl();

    @Test
    public void testStatus() {
        response.setStatus(ResponseStatus.SUCCESS);

        assertThat(response.getStatus(), is(ResponseStatus.SUCCESS));
    }

    @Test
    public void testParameters() {

        response.setParameter("test", true);
        assertThat(response.getParameter("test"), is(true));
    }
}
