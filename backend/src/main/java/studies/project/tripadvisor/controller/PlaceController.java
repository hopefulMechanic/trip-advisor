package studies.project.tripadvisor.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.Comment;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.entity.dto.request.PlaceRequestDTO;
import studies.project.tripadvisor.entity.dto.response.PlaceResponseDTO;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.exception.NoContentException;
import studies.project.tripadvisor.service.PlaceService;
import studies.project.tripadvisor.service.impl.SearchServiceImpl;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private SearchServiceImpl searchService;

    public void setUserService(PlaceService placeService, SearchServiceImpl searchService) {
        this.placeService = placeService;
        this.searchService = searchService;
    }

    @GetMapping("/places")
    public ResponseEntity getPlaces(@RequestParam(value = "filter", required = false) String query) throws NoContentException {
        List<Place> places;
        if (query == null)
            places = placeService.retrievePlaces();
        else
            places = searchService.fuzzySearch(query);
        List<PlaceResponseDTO> placesResponseDto = convertToDto(places);
        return new ResponseEntity(placesResponseDto, HttpStatus.OK);
    }

    @PostMapping("/places")
    public ResponseEntity savePlace(@RequestBody PlaceRequestDTO placeRequestDto) {
        Place place = convertToEntity(placeRequestDto);
        placeService.savePlace(place);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/places/{placeId}")
    public ResponseEntity getPlace(@PathVariable(name = "placeId") Long placeId) throws ElementNotFoundException {
        Place place = placeService.getPlace(placeId);
        PlaceResponseDTO placeResponseDto = convertToDto(place);
        return new ResponseEntity(placeResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/places/{placeId}")
    public ResponseEntity deletePlace(@PathVariable(name = "placeId") Long placeId) throws ElementNotFoundException {
        placeService.deletePlace(placeId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/places/{placeId}")
    public ResponseEntity updatePlace(@RequestBody PlaceRequestDTO placeRequestDto, @PathVariable(name = "placeId") Long placeId) throws ElementNotFoundException {
        Place place = convertToEntity(placeRequestDto);
        placeService.getPlace(placeId);
        place.setId(placeId);
        placeService.updatePlace(place);
        PlaceResponseDTO placeResponseDto = convertToDto(place);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeResponseDto);
    }

    public Place convertToEntity(PlaceRequestDTO placeRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(placeRequestDto, Place.class);
    }

    public PlaceResponseDTO convertToDto(Place place) {
        ModelMapper modelMapper = new ModelMapper();
        PlaceResponseDTO placeResponseDto = modelMapper.map(place, PlaceResponseDTO.class);
        placeResponseDto.setScore(placeResponseDto.getComments()
                .stream()
                .mapToDouble(Comment::getScore)
                .average()
                .orElse(-1));
        return placeResponseDto;
    }

    public List<PlaceResponseDTO> convertToDto(List<Place> places) {
        ModelMapper modelMapper = new ModelMapper();

        Type listType = new TypeToken<List<PlaceResponseDTO>>() {
        }.getType();
        List<PlaceResponseDTO> placesResponseDto = modelMapper.map(places, listType);
        // -1 mowi ze nie ma zadnej oceny
        // NaN tez byl dobrym wyjsciem ale latwiej mi porownac do liczby na froncie :)
        placesResponseDto.forEach(p -> p.setScore(
                p.getComments()
                        .stream()
                        .mapToDouble(Comment::getScore)
                        .average()
                        .orElse(-1)
        ));
        return placesResponseDto;
    }

}
