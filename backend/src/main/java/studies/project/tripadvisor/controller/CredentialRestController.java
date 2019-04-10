package studies.project.tripadvisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studies.project.tripadvisor.entity.Credential;
import studies.project.tripadvisor.service.CredentialService;

@RestController
@RequestMapping("api/auth")
public class CredentialRestController {
    private static final String OK = "ok";
    private static final String NO_OK = "no ok";

    @Autowired
    private CredentialService credentialService;

    public void setCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody() Credential credential) {
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
    public String registerUser(Credential credential) {
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
