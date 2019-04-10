package studies.project.tripadvisor.service;

import studies.project.tripadvisor.entity.User;

import java.util.List;

public interface UserService {

    public List<User> retrieveUsers();

    public User getUser(Long userId);

    public void saveUser(User user);

    public void deleteUser(Long userId);

    public void updateUser(User user);
}
