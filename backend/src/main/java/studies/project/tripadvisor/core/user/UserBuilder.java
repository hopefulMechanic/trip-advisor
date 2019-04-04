package studies.project.tripadvisor.core.user;

public class UserBuilder {

    private Long id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean notifyMe;
    private User user;

    public User build() {
        return user;
    }

    public UserBuilder(String nickname) {
        user = new User(nickname);
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withNotifyMe(Boolean notifyMe) {
        this.notifyMe = notifyMe;
        return this;
    }
}
