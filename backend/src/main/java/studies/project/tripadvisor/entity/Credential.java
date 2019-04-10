package studies.project.tripadvisor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="CREDENTIAL")
public class Credential {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String userName;

    @Column(name="PASSWORD")
    private String password;
}