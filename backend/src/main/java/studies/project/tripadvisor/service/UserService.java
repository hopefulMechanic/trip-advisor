package studies.project.tripadvisor.service;

import studies.project.tripadvisor.entity.User;

import java.util.List;

public interface UserService {

    public List<User> retrieveUsers();

    public User getUser(String userNickname);

    public void saveUser(User user);

    public void deleteUser(String userNickname);

    public void updateUser(User user);
}
