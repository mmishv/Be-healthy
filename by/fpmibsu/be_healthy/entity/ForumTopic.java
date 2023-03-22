package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ForumTopic implements Serializable {
    private int id;
    private int authorId;
    private String title;
    private List<ForumMessage> messages;
    private Date created_on;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<ForumMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ForumMessage> messages) {
        this.messages = messages;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    @Override
    public String toString() {
        return "Author: " + authorId + ", title: " + title;
    }
}
