package studies.project.tripadvisor.core.user;

import studies.project.tripadvisor.core.place.Commerce;

import java.util.List;

public class Owner extends User{

    private List<Commerce> myPlaces;

    public Owner(int id, String nickname, String password, String firstName, String lastName, String email, String notifyMe) {
        super(id, nickname, password, firstName, lastName, email, notifyMe);
    }

}
