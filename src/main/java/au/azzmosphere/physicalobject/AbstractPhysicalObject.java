package au.azzmosphere.physicalobject;

import au.azzmosphere.physicalobject.perspective.Perspective;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPhysicalObject implements PhysicalObject {
    private Perspective perspective;
    Map<String, Object> attributes = new HashMap<>();

    @Override
    public void setPerspective(Perspective perspective) {
        this.perspective = perspective;
    }

    @Override
    public Perspective getPerspective() {
        return perspective;
    }

    @Override
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
