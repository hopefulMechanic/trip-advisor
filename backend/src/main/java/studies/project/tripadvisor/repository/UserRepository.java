package studies.project.tripadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studies.project.tripadvisor.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
