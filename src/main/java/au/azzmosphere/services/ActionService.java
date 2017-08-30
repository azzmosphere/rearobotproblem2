package au.azzmosphere.services;

import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.RulesetCannotBeFoundException;
import au.azzmosphere.worlds.WorldRuleSetFactory;

public interface ActionService {
    void run(Request request) throws RulesetCannotBeFoundException;

    void setWorldRulesetFactory(WorldRuleSetFactory worldRuleSetFactory);
}
