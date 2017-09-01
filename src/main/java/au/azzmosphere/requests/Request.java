package au.azzmosphere.requests;

import au.azzmosphere.physicalobject.PhysicalObject;
import au.azzmosphere.responses.Response;

public interface Request {
    void setPhysicalObject(PhysicalObject physicalObject);
    PhysicalObject getPhysicalObject();

    void setParameter(String key, Object value);
    Object getParameter(String key);

    void setResponse(Response reponse);
    Response getResponse();

    void setType(RequestType requestType);
    RequestType getType();
}
