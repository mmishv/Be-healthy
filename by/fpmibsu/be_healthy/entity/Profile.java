package by.fpmibsu.be_healthy.entity;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class Profile implements Serializable {
    private int id;
    private String name;
    private String email;
    private String login;
    private String password;
    private byte[] avatar;
    private double weight;
    private int height;
    private int age;
    private double activity;
    private HashMap<String, BigDecimal> KBJU_norm;
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
        return name;
    }
    public void setFirstName(String firstName) {
        this.name = firstName;
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Name: "+ name +", email: " + email;
    }

    public HashMap<String, BigDecimal> getKBJU_norm() {
        return KBJU_norm;
    }

    public void setKBJU_norm(HashMap<String, BigDecimal> KBJU_norm) {
        this.KBJU_norm = KBJU_norm;
    }
}