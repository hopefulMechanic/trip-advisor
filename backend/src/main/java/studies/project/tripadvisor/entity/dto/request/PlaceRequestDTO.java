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

    private Double entranceFee;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

    private List<String> categories;

    @NotEmpty
    private Long createdBy;

    public PlaceRequestDTO(String name, String description, String addressLine, String city, String postalCode, String country, Double entranceFee, String email, String phone, List<String> categories, Long createdBy) {
        this.name = name;
        this.description = description;
        this.addressLine = addressLine;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.entranceFee = entranceFee;
        this.email = email;
        this.phone = phone;
        this.categories = categories;
        this.createdBy = createdBy;
    }

}
