package studies.project.tripadvisor.service;

import studies.project.tripadvisor.entity.Place;

import java.util.List;

public interface PlaceService {

    List<Place> retrievePlaces();

    Place getPlace(Long placeId);

    void savePlace(Place place);

    void deletePlace(Long placeId);

    void updatePlace(Place place);

}
