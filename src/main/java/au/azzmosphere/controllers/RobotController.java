package au.azzmosphere.controllers;

import au.azzmosphere.requests.RequestImp;
import au.azzmosphere.responses.Response;
import au.azzmosphere.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class RobotController {
    private ActionService actionService;

    @Autowired
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    @MessageMapping("/robot")
    @SendTo("/topic/robot")
    public Response robotCommand(RequestImp request) {
        try {
            actionService.run(request);
        }
        catch (Exception e) {

        }
        return request.getResponse();
    }
}
