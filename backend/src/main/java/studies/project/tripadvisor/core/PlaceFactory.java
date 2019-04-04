package studies.project.tripadvisor.core;

import studies.project.tripadvisor.core.place.Place;
import studies.project.tripadvisor.core.place.Commerce;
import studies.project.tripadvisor.core.place.Public;

public class PlaceFactory {

    private static Place place = null;

    public static Place getPlace(String placeType) {
        if (placeType.equals("commerce")) {
            place = new Commerce();
        } else if (placeType.equals("public")) {
            place = new Public();
        }
        assert place != null;
        return place;
    }
}
