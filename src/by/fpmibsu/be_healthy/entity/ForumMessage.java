package by.fpmibsu.be_healthy.entity;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ForumMessage implements Serializable {
    private int id;
    private int authorId;
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

    public Date getDateOfPublication() {
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

    @Override
    public String toString() {
        return "AuthorId: " + authorId + ", text: " + text +
                ", date of publication: " + dateOfPublication;
    }
}
