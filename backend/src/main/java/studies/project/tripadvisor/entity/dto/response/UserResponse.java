package studies.project.tripadvisor.entity.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserResponse implements Serializable {

    private Long id;
    private String nickname;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
}
