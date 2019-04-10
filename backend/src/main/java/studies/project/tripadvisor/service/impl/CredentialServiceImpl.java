package studies.project.tripadvisor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studies.project.tripadvisor.entity.Credential;
import studies.project.tripadvisor.repository.CredentialRepository;
import studies.project.tripadvisor.service.CredentialService;

import java.util.Optional;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    public void setCredentialRepository(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Credential getCredential(String userName) {
       Optional<Credential> optCredki = credentialRepository.findById(userName);
       return optCredki.get();
    }

    @Override
    public void saveCredential(Credential credential) {
        credentialRepository.save(credential);
    }
}
