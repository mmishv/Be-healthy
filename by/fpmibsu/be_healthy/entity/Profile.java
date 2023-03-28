package by.fpmibsu.be_healthy.entity;
import java.io.File;
import java.io.Serializable;
import java.util.List;

public class Profile implements Serializable {
    private int id;
    private String name;
    private String email;
    private String login;
    private String password;
    private File avatar;
    private double weight;
    private int height;
    private int age;
    private double activity;
    private List<Article> written_articles;
    private List<Recipe> liked_recipes;
    private List<Recipe> written_recipes;
    private List<ForumTopic> started_topics;
    private List<ForumMessage> messages;
    private List<Meal> meals;
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

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
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

    public List<Article> getWritten_articles() {
        return written_articles;
    }

    public void setWritten_articles(List<Article> written_articles) {
        this.written_articles = written_articles;
    }

    public List<Recipe> getLiked_recipes() {
        return liked_recipes;
    }

    public void setLiked_recipes(List<Recipe> liked_recipes) {
        this.liked_recipes = liked_recipes;
    }

    public List<Recipe> getWritten_recipes() {
        return written_recipes;
    }

    public void setWritten_recipes(List<Recipe> written_recipes) {
        this.written_recipes = written_recipes;
    }

    public List<ForumTopic> getStarted_topics() {
        return started_topics;
    }

    public void setStarted_topics(List<ForumTopic> started_topics) {
        this.started_topics = started_topics;
    }

    public List<ForumMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ForumMessage> messages) {
        this.messages = messages;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Name: "+ name +", email: " + email;
    }

}