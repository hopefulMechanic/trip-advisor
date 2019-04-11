package studies.project.tripadvisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studies.project.tripadvisor.entity.Credential;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.service.CredentialService;
import studies.project.tripadvisor.service.UserService;

@RestController
@RequestMapping("api/auth")
public class CredentialRestController {
    private static final HttpStatus OK = HttpStatus.OK;
    private static final HttpStatus NO_OK = HttpStatus.UNAUTHORIZED;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;

    public void setCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody() Credential credential) {
        if (checkCredential(credential)) {
            return null;
        }
        Credential retrivedCredential = credentialService.getCredential(credential.getUserName());
        if (credential.getPassword().equals(retrivedCredential.getPassword())) {
            return userService.getUser(credential.getUserName());
        } else {
            return null;
        }
    }

    @PostMapping("/register")
    public HttpStatus registerUser(Credential credential) {
        if (checkCredential(credential)) {
            return NO_OK;
        } else {
            credentialService.saveCredential(credential);
            User user = new User();
            user.setNickname(credential.getUserName());
            user.setPassword(credential.getPassword());
            userService.saveUser(user);
            return OK;
        }
    }

    private boolean checkCredential(Credential credential) {
        return credential == null || credential.getUserName() == null || credential.getPassword() == null;
    }
}
