package au.azzmosphere.worlds;

import org.apache.log4j.Logger;

public class InvalidMovementException extends Exception {
    private static final Logger logger = Logger.getLogger(InvalidMovementException.class);

    public InvalidMovementException(String message) {
        super(message);
        logger.error(message, this);
    }
}
