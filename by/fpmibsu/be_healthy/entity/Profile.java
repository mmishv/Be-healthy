package by.fpmibsu.be_healthy.entity;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Profile implements Serializable {
    private int id;
    private String name;
    private String email;
    private String login;
    private String password;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] avatar;
    String base64image;
    private double weight;
    private int height;
    private int age;
    private double activity;
    private String sex;
    private double goal;
    private Role role;

    public Profile() {
    }

    public Profile(int id, String name, String email, String login, String password,
                   double weight, int height, int age, double activity,
                   String sex, double goal, Role role,
                   double k, double b,
                   double j, double u) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.activity = activity;
        this.sex = sex;
        this.goal = goal;
        this.role = role;
        this.KBJU_norm = new HashMap<>();
        KBJU_norm.put("k", BigDecimal.valueOf(k));
        KBJU_norm.put("b", BigDecimal.valueOf(b));
        KBJU_norm.put("j", BigDecimal.valueOf(j));
        KBJU_norm.put("u", BigDecimal.valueOf(u));
    }

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

    public String getBase64image() {
        return base64image;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }
    public int getCalorieRec(){
        if (weight==0 || height==0 || age==0)
            return 0;
        return (int) (((Objects.equals(sex, "женский")) ? 447.6 + 9.2 * weight + 3.1 * height - 4.3 * age :
                        88.36 + 13.4 * weight + 4.8 * height - 5.7 * age) * activity * goal);
    }
    public int getProteinRec(){
        return (int) (getCalorieRec()*0.3/4);
    }

    public int getFatRec(){
        return (int) (getCalorieRec()*0.3/9);
    }
    public int getCarbRec(){
        return (int)(getCalorieRec()*0.4/4);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return getId() == profile.getId() && Double.compare(profile.getWeight(),
                getWeight()) == 0 && getHeight() == profile.getHeight() &&
                getAge() == profile.getAge() && Double.compare(profile.getActivity(),
                getActivity()) == 0 && Double.compare(profile.getGoal(), getGoal()) == 0
                && Objects.equals(getName(), profile.getName()) && Objects.equals(getEmail(),
                profile.getEmail()) && Objects.equals(getLogin(), profile.getLogin()) &&
                Objects.equals(getPassword(), profile.getPassword()) && Arrays.equals(getAvatar(),
                profile.getAvatar()) && Objects.equals(getSex(), profile.getSex()) && Objects.equals(getRole(),
                profile.getRole()) && Objects.equals(getKBJU_norm(), profile.getKBJU_norm());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getEmail(), getLogin(),
                getPassword(), getWeight(), getHeight(),
                getAge(), getActivity(), getSex(), getGoal(), getRole(), getKBJU_norm());
        result = 31 * result + Arrays.hashCode(getAvatar());
        return result;
    }
}