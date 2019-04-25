package studies.project.tripadvisor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "CREDENTIALS")
public class Credential {

    @Id
    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "PASSWORD")
    private String password;

    @OneToOne
    @MapsId
    private User user;

    @Override
    public String toString() {
        return "Credential{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
