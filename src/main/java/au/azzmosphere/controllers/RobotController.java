package au.azzmosphere.controllers;

import au.azzmosphere.physicalobject.perspective.CardinalDirection;
import au.azzmosphere.requests.RequestImp;
import au.azzmosphere.requests.RequestType;
import au.azzmosphere.responses.Response;
import au.azzmosphere.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Iterator;
import java.util.Map;

@Controller
public class RobotController {
    private ActionService actionService;

    @Autowired
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    @MessageMapping("/robot")
    @SendTo("/topic/toyrobot")
    public Map robotCommand(Map<String, Object> command) {

        RequestImp request = getRequest(command);
        try {
            actionService.run(request);
        }
        catch (Exception e) {

        }
        Map response = request.getResponse().getParameters();
        response.put("status", request.getResponse().getStatus());

        return response;
    }

    private RequestImp getRequest(Map<String, Object> command) {
        RequestImp request = new RequestImp();
        Object type = command.get("type");
        command.remove("type");

        for (RequestType r : RequestType.values()) {
            if (r.toString().equals(type)) {
                request.setType(r);
                break;
            }
        }

        Iterator it = command.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            request.setParameter((String) pair.getKey(), pair.getValue());
        }
        return request;
    }
}
