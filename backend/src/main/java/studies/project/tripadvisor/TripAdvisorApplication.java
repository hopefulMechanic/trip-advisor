package studies.project.tripadvisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.repository.PlaceRepository;
import studies.project.tripadvisor.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;

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
        User nonCommercialSaved = userRepository.save(nonCommercial);
        User commercialSavedd = userRepository.save(commercial);

        Place commercialPlace = new Place("Restauracja A", "Test commercial Place",
                "Klimeckiego", "Krakow", "123", "Polska", 100.00, "commercialPlace@gmail.com",
                "123456789", List.of("hotel", "parking"));
        commercialPlace.setCreatedBy(commercialSavedd);
        Place commercialPlace2 = new Place("Restauracja A", "Test commercial Place",
                "Klimeckiego", "Krakow", "123", "Polska", 100.00, "commercialPlace@gmail.com",
                "123456789", List.of("hotel", "parking"));
        commercialPlace2.setCreatedBy(commercialSavedd);

        Place commercialPlace3 = new Place("Hotel A", "Test commercial Place",
                "Klimeckiego", "Krakow", "123", "Polska", 100.00, "commercialPlace@gmail.com",
                "123456789", List.of("hotel", "parking"));
        commercialPlace3.setCreatedBy(commercialSavedd);

        Place commercialPlace4 = new Place("Biblioteka A", "Test commercial Place",
                "Klimeckiego", "Krakow", "123", "Polska", 100.00, "commercialPlace@gmail.com",
                "123456789", List.of("hotel", "parking"));
        commercialPlace4.setCreatedBy(commercialSavedd);

        Place commercialPlace5 = new Place("Kino", "Biblioteka jest dostepna",
                "Klimeckiego", "Krakow", "123", "Polska", 100.00, "commercialPlace@gmail.com",
                "123456789", List.of("hotel", "parking"));
        commercialPlace5.setCreatedBy(commercialSavedd);

        Place nonCommercialPlace = new Place("Restauracja B", "Test none commercial Place",
                "Klimeckiego", "Berlin", "123", "Niemcy", 0.00, "non-commercialPlace@gmail.com",
                "123456789", List.of("food", "5star"));
        nonCommercialPlace.setCreatedBy(nonCommercialSaved);

        placeRepository.save(commercialPlace);
        placeRepository.save(commercialPlace2);
        placeRepository.save(commercialPlace3);
        placeRepository.save(commercialPlace4);
        placeRepository.save(commercialPlace5);
        placeRepository.save(nonCommercialPlace);
    }

    public static void main(String[] args) {
        SpringApplication.run(TripAdvisorApplication.class, args);
    }

}
