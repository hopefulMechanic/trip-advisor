package studies.project.tripadvisor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@Table(name = "PLACE")
public class Place {

    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACE_ID")
    private Long id;

    @ApiModelProperty(required = true)
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ADDRESS_LINE")
    private String addressLine;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ENTRANCE_FEE")
    private Double entranceFee;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @ElementCollection()
    @Column(name = "CATEGORIES")
    private List<String> categories = new ArrayList<>();

    @ApiModelProperty(hidden = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
    @JsonManagedReference
    private Set<Comment> comments;

    public Place(String name, String description, String addressLine, String city, String postalCode, String country, float entranceFee, String email, String phone, List<String> categories) {
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
    }
}