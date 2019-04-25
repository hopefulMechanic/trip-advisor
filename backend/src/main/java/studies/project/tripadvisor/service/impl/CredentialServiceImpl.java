package studies.project.tripadvisor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studies.project.tripadvisor.entity.Credential;
import studies.project.tripadvisor.exception.AlreadyExistException;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.repository.CredentialRepository;
import studies.project.tripadvisor.service.CredentialService;

@Slf4j
@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public Credential getCredential(String nickname) {
        log.info("CredentialService: getCredential");
        if (!credentialRepository.existsById(nickname)) {
            throw new ElementNotFoundException();
        }
        return credentialRepository.getOne(nickname);
    }

    @Override
    public void saveCredential(Credential credential) {
        log.info("CredentialService: saveCredential");
        log.info(credential.toString());
        if (isCredentialNull(credential)) {
            throw new IllegalArgumentException();
        }
        if (credentialRepository.existsById(credential.getNickname())) {
            throw new AlreadyExistException();
        }
        credentialRepository.save(credential);
    }

    @Override
    public void updateCredential(Credential credential, String newPassword) {
        log.info("CredentialService: updateCredential");
        log.info(credential.toString());
        if (isCredentialNull(credential)) {
            throw new IllegalArgumentException();
        }
        if (!credentialRepository.existsById(credential.getNickname())) {
            throw new ElementNotFoundException();
        }
        credential.setPassword(newPassword);
        credentialRepository.save(credential);
    }

    @Override
    public void deleteCredential(Credential credential) {
        log.info("CredentialService: deleteCredential");
        log.info(credential.toString());
        if (!credentialRepository.existsById(credential.getNickname())) {
            throw new ElementNotFoundException();
        }
        credentialRepository.delete(credential);
    }

    private boolean isCredentialNull(Credential credential) {
        return credential == null || credential.getNickname() == null || credential.getPassword() == null;
    }
}
