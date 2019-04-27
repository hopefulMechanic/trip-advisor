package studies.project.tripadvisor.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserDTO implements Serializable {

    private Long id;
    private String nickname;
    private String firstName;
    private String lastName;
    private String email;
}
