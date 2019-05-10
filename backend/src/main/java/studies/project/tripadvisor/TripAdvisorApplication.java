package studies.project.tripadvisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.repository.PlaceRepository;
import studies.project.tripadvisor.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;


@ComponentScan(basePackages = "studies.project.tripadvisor")
@CrossOrigin
@EnableSwagger2
@SpringBootApplication
public class TripAdvisorApplication {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PlaceRepository placeRepository;

    @PostConstruct
    void init() {
        loadTestData();
    }

    void loadTestData() {
        User nonCommercial = new User("regular", "test", "Kamil", "Kleczek", "regular@gmail.com", "regular");
        User commercial = new User("commercial", "test", "Dominik", "Zurek", "comercial@gmail.com", "commercial");
        userRepository.save(nonCommercial);
        userRepository.save(commercial);

        Place commercialPlace = new Place("Commercial Place", "Test commercial Place",
                "Klimeckiego", "Krakow", "123", "Polska", 100.00, "commercialPlace@gmail.com",
                "123456789", List.of("hotel", "parking"));

        Place nonCommercialPlace = new Place("Non commercial Place", "Test none commercial Place",
                "Klimeckiego", "Berlin", "123", "Niemcy", 0.00, "non-commercialPlace@gmail.com",
                "123456789", List.of("food", "5star"));

        placeRepository.save(commercialPlace);
        placeRepository.save(nonCommercialPlace);
    }

    public static void main(String[] args) {
        SpringApplication.run(TripAdvisorApplication.class, args);
    }

}
