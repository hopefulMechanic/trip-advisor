package studies.project.tripadvisor.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import studies.project.tripadvisor.core.user.User;

import java.util.List;

@Repository
public class UserJdbcRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User findById(int id) {
        return jdbcTemplate.queryForObject("select * from TRIP_ADVISOR_USER where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findAll() {
        return jdbcTemplate.query("select * from TRIP_ADVISOR_USER", new UserRowMapper());
    }

    public int insert(User user) {
        return jdbcTemplate.update("INSERT INTO TRIP_ADVISOR_USER (ID, NICKNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, NOTIFY_MY ) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)",
                new Object[] {user.getId(), user.getNickname(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getNotifyMe()});
    }
}
