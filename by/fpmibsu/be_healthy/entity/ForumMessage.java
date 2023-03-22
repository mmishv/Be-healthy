package by.fpmibsu.be_healthy.entity;
import java.io.File;
import java.io.Serializable;
import java.sql.Date;

public class ForumMessage implements Serializable {
    private int id;
    private int authorId;
    private int topic_id;
    private String text;
    private Date dateOfPublication;
    File attachment;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public java.sql.Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    @Override
    public String toString() {
        return "Message author id: " + authorId + ", text: " + text +
                ", date of publication: " + dateOfPublication;
    }
}
