package au.azzmosphere.worlds;

import org.apache.log4j.Logger;

/**
 * Occurs when an attempt to move or remove a physical object is
 * attempted but the object does not yet exist on the world.
 */

public class ObjectNotYetPlacedException extends Exception {
    public static final Logger logger = Logger.getLogger(ObjectNotYetPlacedException.class);

    public ObjectNotYetPlacedException(String message) {
        super(message);
        logger.error("Object does not yet exist in the physical world");
    }
}
