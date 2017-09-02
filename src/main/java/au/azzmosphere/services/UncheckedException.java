package au.azzmosphere.services;

import org.apache.log4j.Logger;

/**
 * An unchecked exception occurs when something unexpected happens. It can be consider
 * a catch all exception.
 */

public class UncheckedException extends Exception {
    private static final Logger logger = Logger.getLogger(UncheckedException.class);

    public UncheckedException(String message) {
        super(message);
        logger.fatal("Unknown exception has occurred", this);
    }
}
