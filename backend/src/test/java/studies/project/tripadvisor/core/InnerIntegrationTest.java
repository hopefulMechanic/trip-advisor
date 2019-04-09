package studies.project.tripadvisor.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studies.project.tripadvisor.core.repository.UserJdbcRepository;
import studies.project.tripadvisor.core.user.User;
import studies.project.tripadvisor.core.user.UserBuilder;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InnerIntegrationTest {

    @Autowired
    private UserJdbcRepository tripAdvisorUserRepository;

    @Test
    public void shouldSaveCreatedUserAndRetrieveHim() {
        // given
        User userToBeSaved = new UserBuilder("testNickname")
                .withId(1234)
                .withFirstName("testFirstName")
                .withLastName("testLastName")
                .withEmail("testEmail")
                .withPassword("haslo")
                .withNotifyMe("truee")
                .build();

        // when
        tripAdvisorUserRepository.insert(userToBeSaved);
        User retrivedUser = tripAdvisorUserRepository.findAll().get(0);

        // then
        assertEquals(userToBeSaved.getId(), retrivedUser.getId());
        assertEquals(userToBeSaved.getNickname(), retrivedUser.getNickname());
        assertEquals(userToBeSaved.getPassword(), retrivedUser.getPassword());
        assertEquals(userToBeSaved.getFirstName(), retrivedUser.getFirstName());
        assertEquals(userToBeSaved.getLastName(), retrivedUser.getLastName());
        assertEquals(userToBeSaved.getEmail(), retrivedUser.getEmail());
    }
}
