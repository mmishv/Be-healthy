package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.ForumMessage;
import by.fpmibsu.be_healthy.dao.ForumMessageDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class ForumMessageService {
    public List<ForumMessage> getAll() throws SQLException {
        return new ForumMessageDao().getAll();
    }

    public ForumMessage getEntityById(long id) throws SQLException {
        return new ForumMessageDao().getEntityById(id);
    }

    public boolean update(ForumMessage entity) throws SQLException {
        return new ForumMessageDao().update(entity);
    }

    public boolean delete(ForumMessage entity) throws SQLException {
        return new ForumMessageDao().delete(entity);
    }

    public boolean create(ForumMessage entity) throws SQLException {
        return new ForumMessageDao().create(entity);
    }

    public List<ForumMessage> getMessagesByUserId(int id) throws SQLException {
        return new ForumMessageDao().getMessagesByUserId(id);
    }

    public String getAllJSON() throws JsonProcessingException, SQLException {
        return new ObjectMapper().writeValueAsString(new ForumMessageService().getAll());
    }
}
