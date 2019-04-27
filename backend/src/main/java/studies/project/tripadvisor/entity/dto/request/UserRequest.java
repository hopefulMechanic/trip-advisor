package studies.project.tripadvisor.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
public class UserRequest implements Serializable {

    private Long id;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    // moze refator do enuma prosze ustalic z Dawidem
    @NotEmpty
    private String role;
}
