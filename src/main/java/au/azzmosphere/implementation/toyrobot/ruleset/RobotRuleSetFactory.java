package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.RulesetCannotBeFoundException;
import au.azzmosphere.worlds.World;
import au.azzmosphere.worlds.WorldRuleSet;
import au.azzmosphere.worlds.WorldRuleSetFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RobotRuleSetFactory implements WorldRuleSetFactory {
    private World world;

    @Autowired
    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public WorldRuleSet getRuleSet(Request request) throws RulesetCannotBeFoundException {
        return null;
    }
}
