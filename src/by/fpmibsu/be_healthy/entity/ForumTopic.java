package by.fpmibsu.be_healthy.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ForumTopic implements Serializable {
    private int id;
    private int authorId;
    private String title;
    private String preview;
    private double messageNumber;
    private Date lastMessageDate;

    public ArrayList<ForumMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<ForumMessage> messages) {
        this.messages = messages;
    }
    public void addMessage(ForumMessage newMessage){
        messages.add(newMessage);
        lastMessageDate = newMessage.getDateOfPublication();
        messageNumber+=1;
    }
    private ArrayList<ForumMessage> messages;
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

    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public double getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(double messageNumber) {
        this.messageNumber = messageNumber;
    }

    @Override
    public String toString() {
        return "Author: " + authorId + ", number of messages: " + messageNumber +
                ", last message date: " + lastMessageDate;
    }
}
