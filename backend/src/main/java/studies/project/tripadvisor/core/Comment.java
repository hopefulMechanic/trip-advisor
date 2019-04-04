package studies.project.tripadvisor.core;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Comment {

    private String text;
    private Date addedDate;
    private Date modifiedDate;
    //private User user;

}
