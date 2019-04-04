package studies.project.tripadvisor.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import studies.project.tripadvisor.core.repository.TripAdvisorUserRepository;
import studies.project.tripadvisor.core.user.User;
import studies.project.tripadvisor.core.user.UserBuilder;

import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@SpringBootTest
@Transactional
public class InnerIntegrationTest {

    @Autowired
    public TripAdvisorUserRepository tripAdvisorUserRepository;

    @Test
    @Sql("/trip-schema.sql")
    public void shouldSaveCreatedUserAndRetrieveHim() {
        // when
        User userToBeSaved = new UserBuilder("testNickname")
                .withId(1234567890L)
                .withFirstName("testFirstName")
                .withLastName("testLastName")
                .withEmail("testEmail")
                .withPassword("haslo")
                .withNotifyMe(true)
                .build();

        Long id = tripAdvisorUserRepository.save(userToBeSaved);

        User retrievedUser = tripAdvisorUserRepository.findById(id).get();

        // then
        assertEquals(userToBeSaved.nickname, retrievedUser.nickname);
        assertEquals(userToBeSaved.getFirstName(), retrievedUser.getFirstName());
        assertEquals(userToBeSaved.getEmail(), retrievedUser.getEmail());
        assertEquals(userToBeSaved.getNotifyMe(), retrievedUser.getNotifyMe());
        assertNotNull(retrievedUser.getId());
        assertNull(userToBeSaved.getId());
    }
}
