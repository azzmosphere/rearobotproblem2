package au.azzmosphere.worlds;

import au.azzmosphere.requests.Request;

public interface WorldRuleSetFactory {
    void setWorld(World world);
    WorldRuleSet getRuleSet(Request request) throws RulesetCannotBeFoundException;
}
