package studies.project.tripadvisor.service;

import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.entity.dto.request.CredentialDTO;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.exception.InvalidPasswordException;

public interface AuthService {
    User loginUser(CredentialDTO credentialDTO) throws InvalidPasswordException, ElementNotFoundException;
}
