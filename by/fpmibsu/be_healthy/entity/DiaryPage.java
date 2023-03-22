package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.*;
import java.sql.Date;
public class DiaryPage implements Serializable {
    private int id;
    private int userId;
    private Date dateOfDiaryPage;
    private List<Meal> meals;
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

    public Date getDateOfDiaryPage() {
        return dateOfDiaryPage;
    }

    public void setDateOfDiaryPage(Date dateOfDiaryPage) {
        this.dateOfDiaryPage = dateOfDiaryPage;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public void setDateOfDiary(Date dateOfDiary) {
        this.dateOfDiaryPage = dateOfDiary;
    }
    @Override
    public String toString() {
        return "Diary page id: "+id+", user: " + userId + ", date: " + dateOfDiaryPage;
    }
}