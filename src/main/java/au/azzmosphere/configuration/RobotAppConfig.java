package au.azzmosphere.configuration;

import au.azzmosphere.implementation.toyrobot.ruleset.RobotRuleSetFactory;
import au.azzmosphere.implementation.toyrobot.services.ActionServiceImpl;
import au.azzmosphere.services.ActionService;
import au.azzmosphere.worlds.World;
import au.azzmosphere.worlds.WorldImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RobotAppConfig {

    @Bean
    public World getWorld() {
        return new WorldImpl(5, 5);
    }

    @Bean
    public ActionService getActionService() {
        return new ActionServiceImpl();
    }

    @Bean
    public RobotRuleSetFactory getRuleSetFactory() {
        return new RobotRuleSetFactory();
    }
}
