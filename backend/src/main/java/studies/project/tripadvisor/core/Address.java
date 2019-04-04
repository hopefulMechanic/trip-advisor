package studies.project.tripadvisor.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postalCode;
    private String country;

}
