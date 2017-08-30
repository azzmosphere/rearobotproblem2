package au.azzmosphere.worlds;

import org.apache.log4j.Logger;

public class OccupiedByAnotherObjectException extends Exception {
    private static final Logger logger = Logger.getLogger(OccupiedByAnotherObjectException.class);

    public OccupiedByAnotherObjectException(String message) {
        super(message);
        logger.error("attempted to place physical object in a area occupied by another object.");
    }
}
