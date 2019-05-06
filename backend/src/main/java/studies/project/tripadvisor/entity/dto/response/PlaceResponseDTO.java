package studies.project.tripadvisor.entity.dto.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import studies.project.tripadvisor.entity.Comment;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class PlaceResponseDTO {

    private Long id;

    private String name;

    private String description;

    private String addressLine;

    private String city;

    private String postalCode;

    private String country;

    private Double entranceFee;

    private String email;

    private String phone;

    private List<String> categories;

    private Set<Comment> comments;

    private Double score;

}
