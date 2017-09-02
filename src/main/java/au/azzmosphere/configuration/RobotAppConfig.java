package au.azzmosphere.configuration;

import au.azzmosphere.implementation.toyrobot.ruleset.RobotRuleSetFactory;
import au.azzmosphere.implementation.toyrobot.services.ActionServiceImpl;
import au.azzmosphere.services.ActionService;
import au.azzmosphere.worlds.World;
import au.azzmosphere.worlds.WorldImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is used to configure the application. You can alter the concrete
 * classes from within here for a new version of the application.
 */

@Configuration
public class RobotAppConfig {

    /**
     *
     * Creates a instance of the world, the standard instance is a grid
     * of 5x5 squares.
     *
      * @return
     */
    @Bean
    public World getWorld() {
        return new WorldImpl(5, 5);
    }

    /**
     *
     * The action service coordinates the request to the business rules, which
     * generate a response.  It them communicates this response to the controller
     *
     * @return
     */
    @Bean
    public ActionService getActionService() {
        return new ActionServiceImpl();
    }

    /**
     * The rule set factory applies the business rules.
     *
     * @return
     */
    @Bean
    public RobotRuleSetFactory getRuleSetFactory() {
        return new RobotRuleSetFactory();
    }
}
