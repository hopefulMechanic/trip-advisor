package studies.project.tripadvisor.api.core;


import studies.project.tripadvisor.api.core.place.Commerce;
import studies.project.tripadvisor.api.core.place.Place;
import studies.project.tripadvisor.api.core.place.Public;

public class PlaceFactory {

    public static Place getPlace(String placeType) {
        if (placeType.equals("commerce")) {
            return new Commerce();
        } else if (placeType.equals("public")) {
            return new Public();
        } else {
            return null;
        }
    }
}
