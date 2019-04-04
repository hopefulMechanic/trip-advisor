package studies.project.tripadvisor.config.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"studies.project.tripadvisor"})
@EnableAutoConfiguration
public class AppConfiguration {
}
