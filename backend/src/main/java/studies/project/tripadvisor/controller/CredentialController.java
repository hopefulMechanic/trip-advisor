package studies.project.tripadvisor.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.Credential;
import studies.project.tripadvisor.service.CredentialService;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api/auth")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody Credential credential) {
        Credential retrievedCredential = credentialService.getCredential(credential.getNickname());
        if (!retrievedCredential.getPassword().equals(credential.getPassword())) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Credential credential) {
        credentialService.saveCredential(credential);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Credential credential) {
        credentialService.deleteCredential(credential);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update/{newPassword}")
    public ResponseEntity updateCredential(@RequestBody Credential credential, @PathVariable(name = "newPassword") String newPassword) {
        credentialService.updateCredential(credential, newPassword);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
