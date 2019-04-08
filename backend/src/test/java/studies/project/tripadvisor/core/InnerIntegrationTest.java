package studies.project.tripadvisor.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studies.project.tripadvisor.core.repository.JdbcTripAdvisorUserRepository;
import studies.project.tripadvisor.core.user.User;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class InnerIntegrationTest {

    @Autowired
    private JdbcTripAdvisorUserRepository tripAdvisorUserRepository;

    @Test
    public void shouldSaveCreatedUserAndRetrieveHim() {
        // when
//        User userToBeSaved = new UserBuilder("testNickname")
//                .withId(1234567890L)
//                .withFirstName("testFirstName")
//                .withLastName("testLastName")
//                .withEmail("testEmail")
//                .withPassword("haslo")
//                .withNotifyMe(true)
//                .build();

        User userToBeSaved = new User(null, "nick", "pass", "fn", "ln", "em", true);


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
