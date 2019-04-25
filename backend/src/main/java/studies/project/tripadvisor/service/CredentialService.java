package studies.project.tripadvisor.service;

import studies.project.tripadvisor.entity.Credential;

public interface CredentialService {

    Credential getCredential(String nickname);

    void saveCredential(Credential credential);

    void updateCredential(Credential credential, String newPassword);

    void deleteCredential(Credential credential);
}
