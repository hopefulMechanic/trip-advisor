package studies.project.tripadvisor.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

import java.util.List;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    public void setUserService(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/places")
    public ResponseEntity getPlaces() throws NoContentException {
        List<Place> places = placeService.retrievePlaces();
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

    private Place convertToEntity(PlaceRequestDTO placeRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        Place place = modelMapper.map(placeRequestDto, Place.class);
        return place;
    }

    private PlaceResponseDTO convertToDto(Place place) {
        ModelMapper modelMapper = new ModelMapper();
        PlaceResponseDTO placeResponseDto = modelMapper.map(place, PlaceResponseDTO.class);
        placeResponseDto.setScore(placeResponseDto.getComments()
                .stream()
                .mapToDouble(Comment::getScore)
                .average()
                .orElse(Double.NaN));
        return placeResponseDto;
    }

    private List<PlaceResponseDTO> convertToDto(List<Place> places) {
        ModelMapper modelMapper = new ModelMapper();
        List<PlaceResponseDTO> placesResponseDto = modelMapper.map(places, List.class);
//        placesResponseDto.stream().filter(o -> o.getComments()).stream().mapToDouble(Comment::getScore));
        return placesResponseDto;
    }

}
