package studies.project.tripadvisor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="USER_NICKNAME")
    private String nickname;

    @Column(name="USER_PASSWORD")
    private String password;

    @Column(name="USER_FIRST_NAME")
    private String firstName;

    @Column(name="USER_LAST_NAME")
    private String lastName;

    @Column(name="USER_EMAIL")
    private String email;

    @Column(name="NOTIFY_ME")
    private String notifyMe;

    public User() {
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public User(Long id, String nickname, String password, String firstName, String lastName, String email, String notifyMe) {
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
