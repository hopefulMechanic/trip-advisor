package studies.project.tripadvisor.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.Comment;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.entity.dto.response.PlaceResponseDTO;
import studies.project.tripadvisor.exception.NoContentException;
import studies.project.tripadvisor.service.PlaceService;
import studies.project.tripadvisor.service.impl.SearchServiceImpl;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api")
public class SearchController {
    @Autowired
    private SearchServiceImpl searchService;

    public void setUserService(SearchServiceImpl searchService, PlaceService placeService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam(value = "query", required = false) String q) throws NoContentException {
        List<Place> searchResults;
        searchResults = searchService.fuzzySearch(q);
        List<PlaceResponseDTO> placesResponseDto = convertToDto(searchResults);
        return new ResponseEntity(placesResponseDto, HttpStatus.OK);
    }

    @Transactional
    private List<PlaceResponseDTO> convertToDto(List<Place> places) {
        ModelMapper modelMapper = new ModelMapper();

        Type listType = new TypeToken<List<PlaceResponseDTO>>() {
        }.getType();
        List<PlaceResponseDTO> placesResponseDto = modelMapper.map(places, listType);

        placesResponseDto.forEach(p -> p.setScore(
                p.getComments()
                        .stream()
                        .mapToDouble(Comment::getScore)
                        .average()
                        .orElse(Double.NaN)
        ));
        return placesResponseDto;
    }
}
