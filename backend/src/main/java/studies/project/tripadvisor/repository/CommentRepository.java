package studies.project.tripadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studies.project.tripadvisor.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}