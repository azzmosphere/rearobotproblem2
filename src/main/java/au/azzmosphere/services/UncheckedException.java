package au.azzmosphere.services;

import org.apache.log4j.Logger;

public class UncheckedException extends Exception {
    private static final Logger logger = Logger.getLogger(UncheckedException.class);

    public UncheckedException(String message) {
        super(message);
        logger.fatal("Unknown exception has occurred", this);
    }
}
