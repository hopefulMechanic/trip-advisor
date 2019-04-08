package studies.project.tripadvisor.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import studies.project.tripadvisor.api.core.Address;
import studies.project.tripadvisor.api.core.Comment;
import studies.project.tripadvisor.api.core.Score;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PlaceDTO {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String type;

    @NotNull
    private Address address;

    // #TODO add categories
    // private CategoriesList categoriesList;

    private LinkedList<Comment> commentList;

    private LinkedList<Score> scoresList;

    private float score;
}
