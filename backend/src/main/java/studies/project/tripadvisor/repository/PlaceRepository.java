package studies.project.tripadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studies.project.tripadvisor.entity.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}