package studies.project.tripadvisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.service.UserService;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/User")
    public List<User> getUser() {
        List<User> users = userService.retrieveUsers();
        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUser(@PathVariable(name = "userId") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/api/users")
    public void saveUser(User user) {
        userService.saveUser(user);
        System.out.println("User Saved Successfully");
    }

    @DeleteMapping("/api/users/{userId}")
    public void deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        System.out.println("User Deleted Successfully");
    }

    @PutMapping("/api/users/{userId}")
    public void updateEmployee(@RequestBody User user,
                               @PathVariable(name = "userId") Long userId) {
        User u = userService.getUser(userId);
        if (u != null) {
            userService.updateUser(user);
        }
    }
}
