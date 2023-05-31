package by.fpmibsu.be_healthy.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Article extends Entity {
    private int id;
    private int authorId;
    private String title;
    private String fulltext;
    private Date dateOfPublication;
    private List<ArticleCategory> categories = new ArrayList<>();
    boolean moderated;

    public Article() {
    }

    public Article(int id, int authorId, String title, String fulltext, Date dateOfPublication, List<ArticleCategory> categories, boolean moderated) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.fulltext = fulltext;
        this.dateOfPublication = dateOfPublication;
        this.categories = categories;
        this.moderated = moderated;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return getId() == article.getId() &&
                getAuthorId() == article.getAuthorId() &&
                isModerated() == article.isModerated() &&
                Objects.equals(getTitle(), article.getTitle()) &&
                Objects.equals(getFulltext(), article.getFulltext()) &&
                Objects.equals(getDateOfPublication(), article.getDateOfPublication()) &&
                Objects.equals(getCategories(), article.getCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthorId(), getTitle(),
                getFulltext(), getDateOfPublication(), getCategories(), isModerated());
    }

    @Override
    public String toString() {
        return "Article author: " + authorId + ", title: " + title  + ", date of publication: " + dateOfPublication;
    }
}
