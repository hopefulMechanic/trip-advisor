package studies.project.tripadvisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"studies.project.tripadvisor"})
public class TripAdvisorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripAdvisorApplication.class, args);
    }
}
