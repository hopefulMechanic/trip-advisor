package studies.project.tripadvisor.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.entity.dto.request.CredentialDTO;
import studies.project.tripadvisor.entity.dto.request.UserRequest;
import studies.project.tripadvisor.entity.dto.response.UserResponse;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.exception.InvalidPasswordException;
import studies.project.tripadvisor.service.AuthService;
import studies.project.tripadvisor.service.UserService;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody CredentialDTO credentialDTO) {
        try {
            User user = authService.loginUser(credentialDTO);
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setNickname(user.getNickname());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setEmail(user.getEmail());
            userResponse.setRole(user.getRole());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (ElementNotFoundException | InvalidPasswordException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequest userRequest) {
        if (!userService.isNicknameTaken(userRequest.getNickname())) {
            User user = new User();
            user.setNickname(userRequest.getNickname());
            user.setPassword(userRequest.getPassword());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setRole(userRequest.getRole());
            userService.saveUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity delete(@RequestBody CredentialDTO credentialDTO) {
//        List<User> matchingUsers = getMatchingUsers(credentialDTO);
//        if (matchingUsers.isEmpty()) {
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        } else {
//            userService.deleteUser(matchingUsers.get(0).getId());
//            return new ResponseEntity(HttpStatus.OK);
//        }
//    }
//
//    @PutMapping("/update/{newPassword}")
//    public ResponseEntity updateCredential(@RequestBody CredentialDTO credentialDTO, @PathVariable(name = "newPassword") String newPassword) {
//        List<User> matchingUsers = getMatchingUsers(credentialDTO);
//        if (matchingUsers.isEmpty()) {
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        } else {
//            User user = matchingUsers.get(0);
//            user.setPassword(newPassword);
//            userService.saveUser(user);
//            return new ResponseEntity(HttpStatus.OK);
//        }
//    }
}
