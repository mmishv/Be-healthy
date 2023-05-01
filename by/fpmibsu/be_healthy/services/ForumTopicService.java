package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.ForumTopic;
import by.fpmibsu.be_healthy.dao.ForumTopicDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class ForumTopicService {
    public List<ForumTopic> getAll() throws SQLException {
        return new ForumTopicDao().getAll();
    }


    public ForumTopic getEntityById(long id) throws SQLException {
        return new ForumTopicDao().getEntityById(id);
    }


    public boolean update(ForumTopic entity) throws SQLException {
        return new ForumTopicDao().update(entity);
    }

    public boolean delete(ForumTopic entity) throws SQLException {
        return new ForumTopicDao().delete(entity);
    }

    public boolean create(ForumTopic entity) throws SQLException {
        return new ForumTopicDao().create(entity);
    }

    public List<ForumTopic> getTopicsByAuthorId(int id) throws SQLException {
        return new ForumTopicDao().getTopicsByAuthorId(id);
    }

    public String getAllJSON() throws JsonProcessingException, SQLException {
        return new ObjectMapper().writeValueAsString(new ForumTopicService().getAll());
    }
}
