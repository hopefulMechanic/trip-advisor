package studies.project.tripadvisor.core.repository;

import io.vavr.control.Option;
import studies.project.tripadvisor.core.user.User;

public interface TripAdvisorUserRepository {

    public Long save(User user);

    public Option<User> findById(Long id);
}
