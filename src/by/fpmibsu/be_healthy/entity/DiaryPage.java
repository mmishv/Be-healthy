package src.by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.*;
public class DiaryPage implements Serializable {
    private int id;
    private int userId;
    private Date dateOfDiaryPage;
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
    @Override
    public String toString() {
        return "User: " + userId + ", date: " + dateOfDiaryPage;
    }
}