package studies.project.tripadvisor.core.repository;

import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import studies.project.tripadvisor.core.user.User;

@Repository
public class JdbcTripAdvisorUserRepository implements TripAdvisorUserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final RowMapper<User> userMapper = new TripAdvisorUserEntityMapper();

    @Autowired
    public JdbcTripAdvisorUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Long save(User user) {
        KeyHolder keyHolder = insertUser(new BeanPropertySqlParameterSource(user));
        return Option.of(keyHolder.getKey())
                .map(Number::longValue)
                .get();
    }

    private KeyHolder insertUser(SqlParameterSource parameters) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(
                "INSERT INTO TRIP_ADVISOR_USER (ID, NICKNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, NOTIFY_MY )" +
                        "VALUES(SEQ_TRIP_ADVISOR_USER_ID.nextval, :nickname, :password, :firstName, :lastName, :email, :notifyMe)",
                parameters,
                keyHolder,
                new String[]{"ID"});
        return keyHolder;
    }

    @Override
    public Option<User> findById(Long id) {
        return Try.of(() -> queryForUserById(id))
                .map(Option::of)
                .get();
    }

    private User queryForUserById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT ID, NICKNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, NOTIFY_MY " +
                        "FROM TRIP_ADVISOR_USER " +
                        "WHERE ID = ?",
                new Object[]{id},
                userMapper);
    }
}
