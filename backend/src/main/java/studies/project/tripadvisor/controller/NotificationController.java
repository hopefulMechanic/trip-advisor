package studies.project.tripadvisor.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.service.NotificationService;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("{userId}")
    public ResponseEntity retrieveMessages(@PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        return new ResponseEntity(notificationService.retrieveMessages(userId), HttpStatus.OK);
    }

    @PostMapping("notify/{placeId}")
    public ResponseEntity notifyObservers(@RequestBody String message,
                                          @PathVariable(name = "placeId") Long placeId) {
        notificationService.notifyObservers(placeId, message);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("add/{placeId}/{userId}")
    public ResponseEntity addObserver(@PathVariable(name = "placeId") Long placeId,
                                      @PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        notificationService.addObserver(placeId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("remove/{placeId}/{userId}")
    public ResponseEntity removeObserver(@PathVariable(name = "placeId") Long placeId,
                                         @PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        notificationService.removeObserver(placeId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
