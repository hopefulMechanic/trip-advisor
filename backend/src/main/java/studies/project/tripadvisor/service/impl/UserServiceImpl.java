package studies.project.tripadvisor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.repository.UserRepository;
import studies.project.tripadvisor.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> retrieveUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getUser(String userNickname) {
        Optional<User> optEmp = userRepository.findById(userNickname);
        return optEmp.get();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(String userNickname){
        userRepository.deleteById(userNickname);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
