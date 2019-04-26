package studies.project.tripadvisor.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.entity.dto.CredentialDTO;
import studies.project.tripadvisor.entity.dto.UserDTO;
import studies.project.tripadvisor.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api/auth")
public class CredentialController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody CredentialDTO credentialDTO) {
        List<User> matchingUsers = getMatchingUsers(credentialDTO);
        if (matchingUsers.isEmpty()) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else {
            User user = matchingUsers.get(0);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setNickname(user.getNickname());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
            return new ResponseEntity(userDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CredentialDTO credentialDTO) {
        List<User> matchingUsers = getMatchingUsers(credentialDTO);
        if (matchingUsers.isEmpty()) {
            User user = new User();
            user.setNickname(credentialDTO.getNickname());
            user.setPassword(credentialDTO.getPassword());
            userService.saveUser(user);
            User newUser = getMatchingUsers(credentialDTO).get(0);
            return new ResponseEntity(newUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody CredentialDTO credentialDTO) {
        List<User> matchingUsers = getMatchingUsers(credentialDTO);
        if (matchingUsers.isEmpty()) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else {
            userService.deleteUser(matchingUsers.get(0).getId());
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping("/update/{newPassword}")
    public ResponseEntity updateCredential(@RequestBody CredentialDTO credentialDTO, @PathVariable(name = "newPassword") String newPassword) {
        List<User> matchingUsers = getMatchingUsers(credentialDTO);
        if (matchingUsers.isEmpty()) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else {
            User user = matchingUsers.get(0);
            user.setPassword(newPassword);
            userService.saveUser(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    private List<User> getMatchingUsers(@RequestBody CredentialDTO credentialDTO) {
        return userService.retrieveUsers().stream()
                .filter(user -> user.getNickname().equals(credentialDTO.getNickname()))
                .filter(user -> user.getPassword().equals(credentialDTO.getPassword()))
                .collect(Collectors.toList());
    }
}
