package studies.project.tripadvisor.service;

import studies.project.tripadvisor.entity.User;

import java.util.List;

public interface UserService {

    List<User> retrieveUsers();

    User getUser(Long userId);

    void saveUser(User user);

    void deleteUser(Long userId);

    void updateUser(User user);
}
