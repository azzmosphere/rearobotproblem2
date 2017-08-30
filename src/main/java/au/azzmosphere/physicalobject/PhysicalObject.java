package au.azzmosphere.physicalobject;

import au.azzmosphere.physicalobject.perspective.Perspective;

import java.util.Map;

public interface PhysicalObject {
    void setPerspective(Perspective perspective);

    Perspective getPerspective();

    /**
     * sets any further non standard attributes that the physical
     * object requires.  Such as 'White' for a chess game.
     *
     * @param attributes
     */
    void setAttributes(Map<String, Object> attributes);

    /**
     * returns attributes.
     *
     * @return
     */
    Map<String, Object> getAttributes();
}
