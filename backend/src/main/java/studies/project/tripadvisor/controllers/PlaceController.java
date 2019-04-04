package studies.project.tripadvisor.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studies.project.tripadvisor.exceptions.InvalidElementException;
import studies.project.tripadvisor.models.PlaceDTO;
import studies.project.tripadvisor.services.PlaceService;

import java.rmi.ServerException;

@Slf4j
@RestController
@RequestMapping("api/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/add")
    public PlaceDTO addNewPlace(@RequestBody PlaceDTO placeDto) throws ServerException, InvalidElementException {
        log.info("Adding new place");
        return placeService.add(placeDto);
    }

}
