package src.by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.Date;
public class Article implements Serializable {
    private int id;
    private int authorId;
    private String title;
    private String fulltext;
    private Date dateOfPublication;
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

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    @Override
    public String toString() {
        return "Author: " + authorId + ", title: " + title  + ", date of publication: " + dateOfPublication;
    }
}
