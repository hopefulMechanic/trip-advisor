package studies.project.tripadvisor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.exception.NoContentException;
import studies.project.tripadvisor.repository.PlaceRepository;
import studies.project.tripadvisor.service.PlaceService;

import java.util.List;

@Slf4j
@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public void setPlaceRepository(PlaceRepository placeRepository) {
        log.info("PlaceService: setting place repository");
        this.placeRepository = placeRepository;
    }

    public List<Place> retrievePlaces() {
        log.info("PlaceService: retrievePlaces");
        List<Place> places = placeRepository.findAll();
        if (places.isEmpty())
            throw new NoContentException();
        return places;
    }

    public Place getPlace(Long placeId) {
        log.info("PlaceService: getPlace");
        if (!placeRepository.existsById(placeId))
            throw new ElementNotFoundException();
        Place place = placeRepository.getOne(placeId);
        return place;
    }

    public void savePlace(Place place) {
        log.info("PlaceService: savePlace");
        placeRepository.save(place);
    }

    public void deletePlace(Long placeId) {
        log.info("PlaceService: deletePlace");
        if (!placeRepository.existsById(placeId))
            throw new ElementNotFoundException();
        placeRepository.deleteById(placeId);
    }

    public void updatePlace(Place place) {
        log.info("PlaceService: updatePlace");
        placeRepository.save(place);
    }

}
