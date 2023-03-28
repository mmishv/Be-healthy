package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;
public class Meal implements Serializable {
    private int id;
    private String name;
    private Time timeOfMeal;
    private Date dateOfMeal;
    private List<MealProduct> products;
    private int user_id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTimeOfMeal() {
        return timeOfMeal;
    }

    public void setTimeOfMeal(Time timeOfMeal) {
        this.timeOfMeal = timeOfMeal;
    }

    public List<MealProduct> getProducts() {
        return products;
    }

    public void setProducts(List<MealProduct> products) {
        this.products = products;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDateOfMeal() {
        return dateOfMeal;
    }

    public void setDateOfMeal(Date dateOfMeal) {
        this.dateOfMeal = dateOfMeal;
    }

    @Override
    public String toString() {
        return "Meal, name: "
                + name+ ", time: " + timeOfMeal;
    }

}