package studies.project.tripadvisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@ComponentScan(basePackages = "studies.project.tripadvisor")
@CrossOrigin
@EnableSwagger2
@SpringBootApplication
public class TripAdvisorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripAdvisorApplication.class, args);
    }

}
