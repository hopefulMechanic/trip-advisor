package studies.project.tripadvisor.core.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private Long id;
    public final String nickname;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean notifyMe;

    public User(String nickname) {
        this.nickname = nickname;
    }

    public User(Long id, String nickname, String password, String firstName, String lastName, String email, Boolean notifyMe) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.notifyMe = notifyMe;
    }
}
