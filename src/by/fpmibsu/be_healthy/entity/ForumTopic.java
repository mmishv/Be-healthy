package src.by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ForumTopic implements Serializable {
    private int id;
    private int authorId;
    private String title;
    private String preview;
    private List<ForumMessage> messages;

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

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public List<ForumMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ForumMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Author: " + authorId + ", title: " + title +
                ", preview: " + preview;
    }
}
