package studies.project.tripadvisor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "COMMENT")
public class Comment {

    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @ApiModelProperty(required = true)
    @Column(name = "TEXT")
    private String text;

    @ApiModelProperty(hidden = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    @ApiModelProperty(hidden = true)
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @ApiModelProperty(hidden = true)
    @Column(name = "MODIFY_DATE")
    private String modifyDate;
}
