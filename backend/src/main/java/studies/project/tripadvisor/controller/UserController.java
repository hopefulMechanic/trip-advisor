package studies.project.tripadvisor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.exception.NoContentException;
import studies.project.tripadvisor.service.UserService;

import java.util.List;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() throws NoContentException {
        List<User> users = userService.retrieveUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity getUser(@PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        User user = userService.getUser(userId);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity updateUser(@RequestBody User user,
                                         @PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        userService.getUser(userId);
        user.setId(userId);
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
