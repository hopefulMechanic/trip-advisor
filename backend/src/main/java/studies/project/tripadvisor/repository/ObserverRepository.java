package studies.project.tripadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studies.project.tripadvisor.entity.Observer;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.entity.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ObserverRepository extends JpaRepository<Observer, Long> {

    Optional<List<Observer>> findByPlace(Place place);

    Boolean existsByPlaceAndUser(Place place, User user);

    @Transactional
    void deleteByPlaceAndUser(Place place, User user);
}
