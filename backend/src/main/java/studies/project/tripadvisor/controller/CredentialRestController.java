package studies.project.tripadvisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studies.project.tripadvisor.entity.Credential;
import studies.project.tripadvisor.service.CredentialService;

@RestController
@RequestMapping("api/auth")
public class CredentialRestController {
    private static final HttpStatus OK = HttpStatus.OK;
    private static final HttpStatus NO_OK = HttpStatus.UNAUTHORIZED;

    @Autowired
    private CredentialService credentialService;

    public void setCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/login")
    public HttpStatus loginUser(@RequestBody() Credential credential) {
        if (checkCredential(credential)) {
            return NO_OK;
        }
        Credential retrivedCredential = credentialService.getCredential(credential.getUserName());
        if (credential.getPassword().equals(retrivedCredential.getPassword())) {
            return OK;
        } else {
            return NO_OK;
        }
    }

    @PostMapping("/register")
    public HttpStatus registerUser(Credential credential) {
        if (checkCredential(credential)) {
            return NO_OK;
        } else {
            credentialService.saveCredential(credential);
            return OK;
        }
    }

    private boolean checkCredential(Credential credential) {
        return credential == null || credential.getUserName() == null || credential.getPassword() == null;
    }
}
