package studies.project.tripadvisor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.entity.dto.request.CredentialDTO;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.exception.InvalidPasswordException;
import studies.project.tripadvisor.repository.UserRepository;
import studies.project.tripadvisor.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loginUser(CredentialDTO credentialDTO) {
        User user = userRepository.findByNickname(credentialDTO.getNickname())
                .orElseThrow(ElementNotFoundException::new);

        if (!user.getPassword().equals(credentialDTO.getPassword())) {
            throw new InvalidPasswordException();
        }

        return user;
    }
}
