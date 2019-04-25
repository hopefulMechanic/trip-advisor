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

    @GetMapping("/users/{nickname}")
    public ResponseEntity getUser(@PathVariable(name = "nickname") String nickname) throws ElementNotFoundException {
        User user = userService.getUser(nickname);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{nickname}")
    public ResponseEntity deleteUser(@PathVariable(name = "nickname") String nickname) throws ElementNotFoundException {
        userService.deleteUser(nickname);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/users/{nickname}")
    public ResponseEntity updateUser(@RequestBody User user,
                                         @PathVariable(name = "nickname") String nickname) throws ElementNotFoundException {
        userService.getUser(nickname);
        user.setNickname(nickname);
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
