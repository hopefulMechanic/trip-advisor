package studies.project.tripadvisor.service;

import studies.project.tripadvisor.entity.User;

import java.util.List;

public interface UserService {

    List<User> retrieveUsers();

    User getUser(String nickname);

    void saveUser(User user);

    void deleteUser(String nickname);

    void updateUser(User user);
}
