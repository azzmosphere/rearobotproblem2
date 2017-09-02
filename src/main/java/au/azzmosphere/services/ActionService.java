package au.azzmosphere.services;

import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.RulesetCannotBeFoundException;
import au.azzmosphere.worlds.WorldRuleSetFactory;

/**
 * receives request from controller and proxy's this request to the appropriate Business Rule (BR)
 * class. That is defined by the Business Rule factory.
 */

public interface ActionService {
    void run(Request request) throws RulesetCannotBeFoundException, UncheckedException;

    void setWorldRulesetFactory(WorldRuleSetFactory worldRuleSetFactory);
}
