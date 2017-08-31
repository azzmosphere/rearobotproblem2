package au.azzmosphere.responses;

import java.util.Map;

public interface Response {
    void setStatus(ResponseStatus status);
    ResponseStatus getStatus();
    void setParameter(String key, Object value);
    Object getParameter(String key);
    void setParameterIfNull(String key, Object value);
    Map getParameters();
}
