package studies.project.tripadvisor.service;

import studies.project.tripadvisor.entity.Credential;

public interface CredentialService {

    public Credential getCredential(String userName);

    public void saveCredential(Credential credential);
}
