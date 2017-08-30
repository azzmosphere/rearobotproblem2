package au.azzmosphere.worlds;


import org.apache.log4j.Logger;

public class CoordinateOutOfBoundsException extends Exception {
    private static final Logger logger  =  Logger.getLogger(CoordinateOutOfBoundsException.class);

    public CoordinateOutOfBoundsException(String message) {
        super(message);
        logger.error(message, this);
    }
}
