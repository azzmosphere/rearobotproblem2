package au.azzmosphere.worlds;

import org.apache.log4j.Logger;

public class RulesetCannotBeFoundException extends Exception {
    private static final Logger logger = Logger.getLogger(RulesetCannotBeFoundException.class);

    public RulesetCannotBeFoundException(String message) {
        super(message);
        logger.error(message, this);
    }
}
