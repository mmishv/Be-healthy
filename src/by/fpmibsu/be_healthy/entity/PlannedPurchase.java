package src.by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.Date;

public class PlannedPurchase implements Serializable {
    private int id;
    private int userId;
    private String title;
    private Date dateAdded;
    boolean isDone;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "User id: " + userId +", date: "+dateAdded + "title: " + title;
    }
}
