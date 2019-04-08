package studies.project.tripadvisor.api.core.place;


import lombok.Getter;
import lombok.Setter;
import studies.project.tripadvisor.api.core.Address;
import studies.project.tripadvisor.api.core.Comment;
import studies.project.tripadvisor.api.core.Score;

import java.util.LinkedList;

@Getter
@Setter
public class Place {
    private Long placeId;
    private String name;
    private String type;
    private Address address;
    private LinkedList<Comment> commentList;
    private LinkedList<Score> scoresList;
    // #TODO add categories
    // private CategoriesList categoriesList;
    private float score;
}
