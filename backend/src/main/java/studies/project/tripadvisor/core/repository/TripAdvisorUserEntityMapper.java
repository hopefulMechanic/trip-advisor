package studies.project.tripadvisor.core.repository;

import io.vavr.control.Try;
import org.springframework.jdbc.core.RowMapper;
import studies.project.tripadvisor.core.user.User;

import java.sql.ResultSet;

public class TripAdvisorUserEntityMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) {
        return Try.of(() -> new User(resultSet.getObject("ID", Long.class),
                resultSet.getString("NICKNAME"),
                resultSet.getString("PASSWORD"),
                resultSet.getString("FIRST_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("EMAIL"),
                resultSet.getObject("NOTIFY_MY", Boolean.class)))
                .get();
    }
}
