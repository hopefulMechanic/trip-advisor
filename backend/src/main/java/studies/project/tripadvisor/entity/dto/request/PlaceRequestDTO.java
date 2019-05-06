package studies.project.tripadvisor.entity.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class PlaceRequestDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String addressLine;

    @NotEmpty
    private String city;

    @NotEmpty
    private String postalCode;

    @NotEmpty
    private String country;

    private float entranceFee;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

    private List<String> categories;

}
