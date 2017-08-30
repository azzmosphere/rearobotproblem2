package au.azzmosphere.responses;

import java.util.HashMap;
import java.util.Map;

public class ResponseImpl implements Response {
    private ResponseStatus status;
    private Map<String, Object> parameters = new HashMap<>();

    @Override
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public void setParameter(String key, Object value) {
        parameters.put(key, value);
    }

    @Override
    public Object getParameter(String key) {
        return parameters.get(key);
    }
}
