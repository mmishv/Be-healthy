package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Article implements Serializable {
    private int id;
    private int authorId;
    private String title;
    private String fulltext;
    private Date dateOfPublication;
    private List<ArticleCategory> categories;
    boolean moderated;
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

    public List<ArticleCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ArticleCategory> categories) {
        this.categories = categories;
    }

    public boolean isModerated() {
        return moderated;
    }

    public void setModerated(boolean moderated) {
        this.moderated = moderated;
    }

    @Override
    public String toString() {
        return "Article author: " + authorId + ", title: " + title  + ", date of publication: " + dateOfPublication;
    }
}
