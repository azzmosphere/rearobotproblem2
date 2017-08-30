package au.azzmosphere.requests;

import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.responses.Response;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRequest implements Request {
    private PhysicalObject physicalObject;
    private Map<String, Object> parameters = new HashMap<>();
    private Response response;

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
    public void setReponse(Response reponse) {
        this.response = response;
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
