package studies.project.tripadvisor.core.user;

public class UserBuilder {

    private int id;
    private String nickname;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String notifyMe;

    public User build() {
        return new User(id, nickname, password, firstName, lastName, email, notifyMe);
    }

    public UserBuilder(String nickname) {
        this.nickname = nickname;
    }

    public UserBuilder withId(int id) {
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

    public UserBuilder withNotifyMe(String notifyMe) {
        this.notifyMe = notifyMe;
        return this;
    }
}
