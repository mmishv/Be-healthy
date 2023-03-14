package src.by.fpmibsu.be_healthy.entity;
import java.io.File;
import java.io.Serializable;
public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    File avatar;
    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "First name: "+ firstName + ", last name: " + lastName + ", email: " + email;
    }

}