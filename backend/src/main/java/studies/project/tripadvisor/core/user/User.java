package studies.project.tripadvisor.core.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private int id;
    public String nickname;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String notifyMe;

    public User() {

    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public User(int id, String nickname, String password, String firstName, String lastName, String email, String notifyMe) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.notifyMe = notifyMe;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", notifyMe=" + notifyMe +
                '}';
    }
}
