package studies.project.tripadvisor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.exception.NoContentException;
import studies.project.tripadvisor.repository.UserRepository;
import studies.project.tripadvisor.service.UserService;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> retrieveUsers() {
        log.info("UserService: retrieveUsers");
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoContentException();
        }
        return users;
    }

    public User getUser(Long userId) {
        log.info("UserService: getUser");
        if (!userRepository.existsById(userId)) {
            throw new ElementNotFoundException();
        }
        return userRepository.getOne(userId);
    }

    public void saveUser(User user) {
        log.info("UserService: saveUser");
        log.info(user.toString());
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        log.info("UserService: deleteUser");
        if (!userRepository.existsById(userId)) {
            throw new ElementNotFoundException();
        }
        userRepository.deleteById(userId);
    }

    public void updateUser(User user) {
        log.info("UserService: updateUser");
        log.info(user.toString());
        userRepository.save(user);
    }
}
