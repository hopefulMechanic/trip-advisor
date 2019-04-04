package studies.project.tripadvisor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import studies.project.tripadvisor.core.Address;
import studies.project.tripadvisor.core.Comment;
import studies.project.tripadvisor.core.Score;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PlaceDTO {
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
