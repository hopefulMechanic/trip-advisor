package studies.project.tripadvisor.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.models.CredentialsDTO;

@Slf4j
@RestController
@RequestMapping("api/auth")
public class AuthController {
    @PostMapping("/login")
    public String isUserLoggedIn(@RequestBody() CredentialsDTO credentials) {
        log.info("Is user Logged {} {}", credentials.getPassword(), credentials.getUserName());
        return "ok";
    }
}


