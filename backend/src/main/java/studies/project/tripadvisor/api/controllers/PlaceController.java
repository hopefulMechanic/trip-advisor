package studies.project.tripadvisor.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.api.core.place.Place;
import studies.project.tripadvisor.api.exceptions.InvalidElementException;
import studies.project.tripadvisor.api.models.PlaceDTO;
import studies.project.tripadvisor.api.services.PlaceService;

import java.rmi.ServerException;

@Slf4j
@RestController
@RequestMapping("api/place")
public class PlaceController {
    private final PlaceService placeService;
    private final ModelMapper mapper = new ModelMapper();

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/add")
    public Long add(@RequestBody PlaceDTO placeDto) throws ServerException, InvalidElementException {
        log.info("Adding new place");
        //mapper z placeDto na place
        final Place place = mapper.map(placeDto, Place.class);
        return placeService.add(place);
    }

    @GetMapping("/")
    public PlaceDTO get() throws ServerException, InvalidElementException {
        return null;
    }

}
