package au.azzmosphere.responses;

/**
 * the result of processing a request.
 */

public enum ResponseStatus {
    SUCCESS,   // processed without issue.
    INVALID,   // invalid parameters stopped request from being processed.
    FAIL,      // attempted to process but failed. This could be due to world issues.
    EXCEPTION  // an exception was thrown while trying to process request.
}
