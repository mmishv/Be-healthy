package by.fpmibsu.be_healthy.entity;

import java.io.Serializable;
import java.util.Date;


public class ForumMessage implements Serializable {
    private int id;
    private int authorId;
    private int topic_id;
    private String text;
    private Date dateOfPublication;

    byte[] attachment;

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

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
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
