package studies.project.tripadvisor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.exception.NoContentException;
import studies.project.tripadvisor.service.PlaceService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    public void setUserService(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/places")
    public List<Place> getPlaces() throws NoContentException {
        List<Place> places = placeService.retrievePlaces();
        return places;
    }

    @PostMapping("/places")
    public ResponseEntity savePlace(@RequestBody Place place) {
        placeService.savePlace(place);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/places/{placeId}")
    public Place getPlace(@PathVariable(name = "placeId") Long placeId) throws ElementNotFoundException {
        return placeService.getPlace(placeId);
    }

    @DeleteMapping("/places/{placeId}")
    public void deletePlace(@PathVariable(name = "placeId") Long placeId) throws ElementNotFoundException {
        placeService.deletePlace(placeId);
    }

    @PutMapping("/places/{placeId}")
    public ResponseEntity updatePlace(@RequestBody Place place, @PathVariable(name = "placeId") Long placeId) throws ElementNotFoundException {
        Place p = placeService.getPlace(placeId);
        place.setId(placeId);
        placeService.updatePlace(place);
        return ResponseEntity.status(HttpStatus.CREATED).body(place);
    }

}
