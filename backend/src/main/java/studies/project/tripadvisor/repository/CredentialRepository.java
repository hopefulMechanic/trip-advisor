package studies.project.tripadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studies.project.tripadvisor.entity.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, String> {
}
