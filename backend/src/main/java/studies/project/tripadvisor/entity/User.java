package studies.project.tripadvisor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NOTIFY_ME")
    private String notifyMe;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Credential credential;

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", notifyMe=" + notifyMe + '\'' +
                ", credential=" + credential +
                '}';
    }
}
