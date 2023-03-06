package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.*;

public class DiaryPage implements Serializable {
    private int id;
    private int userId;
    private Date dateOfDiaryPage;
    private ArrayList<Meal> meals;
    public DiaryPage() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateOfDiary() {
        return dateOfDiaryPage;
    }

    public void setDateOfDiary(Date dateOfDiary) {
        this.dateOfDiaryPage = dateOfDiary;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public void addMeal(Meal newMeal){
        meals.add(newMeal);
    }

    @Override
    public String toString() {
        return "User: " + userId + ", date: " + dateOfDiaryPage + ", number of meals: " + meals.size();
    }
}