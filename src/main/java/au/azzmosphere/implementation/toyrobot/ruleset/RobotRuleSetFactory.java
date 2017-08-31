package au.azzmosphere.implementation.toyrobot.ruleset;

import au.azzmosphere.requests.Request;
import au.azzmosphere.worlds.RulesetCannotBeFoundException;
import au.azzmosphere.worlds.World;
import au.azzmosphere.worlds.WorldRuleSet;
import au.azzmosphere.worlds.WorldRuleSetFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RobotRuleSetFactory implements WorldRuleSetFactory {
    private World world;

    @Autowired
    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public WorldRuleSet getRuleSet(Request request) throws RulesetCannotBeFoundException {
        WorldRuleSet worldRuleSet = null;

        try {
            switch (request.getType()) {
                case MOVE:
                    worldRuleSet = new Move();
                    break;
                case LEFT:
                    worldRuleSet = new Left();
                    break;
                case RIGHT:
                    worldRuleSet = new Right();
                    break;
                case REPORT:
                    worldRuleSet = new Report();
                    break;
                case PLACE:
                    worldRuleSet = new Place();
            }
        }
        catch (NullPointerException e) {
            throw new RulesetCannotBeFoundException("request type was not defined");
        }

        if (worldRuleSet == null) {
            throw new RulesetCannotBeFoundException("can not find rule set for request type " + request.getType());
        }

        return worldRuleSet;
    }
}
