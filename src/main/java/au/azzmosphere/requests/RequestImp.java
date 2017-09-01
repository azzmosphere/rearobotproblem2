package au.azzmosphere.requests;

import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.responses.Response;

import java.util.HashMap;
import java.util.Map;

public class RequestImp implements Request {
    private PhysicalObject physicalObject;
    private Map<String, Object> parameters = new HashMap<>();
    private Response response;
    private RequestType requestType;

    @Override
    public void setPhysicalObject(PhysicalObject physicalObject) {
        this.physicalObject = physicalObject;
    }

    @Override
    public PhysicalObject getPhysicalObject() {
        return physicalObject;
    }

    @Override
    public void setParameter(String key, Object value) {
        parameters.put(key, value);
    }

    @Override
    public Object getParameter(String key) {
        return parameters.get(key);
    }

    @Override
    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public Response getResponse() {
        return response;
    }

    @Override
    public void setType(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public RequestType getType() {
        return requestType;
    }
}
