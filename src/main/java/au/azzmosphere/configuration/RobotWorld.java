package au.azzmosphere.configuration;

import au.azzmosphere.worlds.World;
import au.azzmosphere.worlds.WorldImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RobotWorld {

    @Bean
    public World getWorld() {
        return new WorldImpl(5, 5);
    }
}
