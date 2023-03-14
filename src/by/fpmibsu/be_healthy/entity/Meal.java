package src.by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.*;
public class Meal implements Serializable {
    private int id;
    private int diaryPageId;
    private String name;
    private Date timeOfMeal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiaryPageId() {
        return diaryPageId;
    }

    public void setDiaryPageId(int diaryPageId) {
        this.diaryPageId = diaryPageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeOfMeal() {
        return timeOfMeal;
    }

    public void setTimeOfMeal(Date timeOfMeal) {
        this.timeOfMeal = timeOfMeal;
    }


    @Override
    public String toString() {
        return "Diary page id: " + diaryPageId + ", name: "
                + name+ ", time: " + timeOfMeal;
    }

}