package au.azzmosphere.physicalobject.perspective;

public interface Perspective {
    /**
     * Set the direction where the physical object faces.
     *
     * @param direction
     */
    void setDirection(CardinalDirection direction);

    /**
     * returns the direction that the physical object is facing.
     *
     * @return
     */
    CardinalDirection getDirection();
}
