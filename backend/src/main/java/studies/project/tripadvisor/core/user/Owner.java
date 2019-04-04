package studies.project.tripadvisor.core.user;

import io.vavr.collection.List;
import studies.project.tripadvisor.core.place.Commerce;

public class Owner extends User{

    private List<Commerce> myPlaces;

    public Owner(Long id, String nickname, String password, String firstName, String lastName, String email, Boolean notifyMe) {
        super(id, nickname, password, firstName, lastName, email, notifyMe);
    }

}
