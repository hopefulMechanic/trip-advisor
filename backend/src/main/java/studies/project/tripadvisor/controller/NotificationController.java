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
@RequestMapping("api")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/notification/{userId}")
    public ResponseEntity retrieveMessages(@PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        return new ResponseEntity(notificationService.retrieveMessages(userId), HttpStatus.OK);
    }

    @PostMapping("/notification/notify/{placeId}")
    public ResponseEntity notifyObservers(@RequestBody String message,
                                          @PathVariable(name = "placeId") Long placeId) {
        log.info("message {}", message);
        notificationService.notifyObservers(placeId, message);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/notification/{placeId}/{userId}")
    public ResponseEntity addObserver(@PathVariable(name = "placeId") Long placeId,
                                      @PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        notificationService.addObserver(placeId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/notification/{placeId}/{userId}")
    public ResponseEntity removeObserver(@PathVariable(name = "placeId") Long placeId,
                                         @PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        notificationService.removeObserver(placeId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/messages")
    public ResponseEntity deleteMessages(@PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        notificationService.deleteMessages(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/notification/check/{placeId}/{userId}")
    public ResponseEntity checkIfObserve(@PathVariable(name = "placeId") Long placeId,
                                         @PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        if (notificationService.checkIfObserve(placeId, userId)) {
            return new ResponseEntity(Boolean. TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }
}
