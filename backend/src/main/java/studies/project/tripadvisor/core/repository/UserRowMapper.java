package studies.project.tripadvisor.core.repository;

import org.springframework.jdbc.core.RowMapper;
import studies.project.tripadvisor.core.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getInt("ID"),
                resultSet.getString("NICKNAME"),
                resultSet.getString("PASSWORD"),
                resultSet.getString("FIRST_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("EMAIL"),
                resultSet.getString("NOTIFY_MY"));
    }
}
