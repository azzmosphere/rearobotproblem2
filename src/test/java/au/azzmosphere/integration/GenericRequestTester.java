package au.azzmosphere.integration;

import au.azzmosphere.requests.Request;
import au.azzmosphere.requests.RequestImp;
import au.azzmosphere.requests.RequestType;
import au.azzmosphere.responses.Response;
import au.azzmosphere.services.ActionService;

/**
 * Generic Test helper that performs a specific action of the following
 * type:
 *    MOVE
 *    LEFT
 *    RIGHT
 *    REPORT
 */
public class GenericRequestTester {
    public static Response performAction(ActionService actionService, RequestType requestType) throws Exception {
        Request request = new RequestImp();
        request.setType(requestType);
        actionService.run(request);
        return request.getResponse();
    }

    public static Response performPlace(ActionService actionService, String xpos, String ypos, String perspective) throws Exception {
        Request request = new RequestImp();
        request.setType(RequestType.PLACE);
        request.setParameter("xpos", xpos);
        request.setParameter("ypos", ypos);
        request.setParameter("perspective", perspective);
        actionService.run(request);

        return request.getResponse();
    }
}
