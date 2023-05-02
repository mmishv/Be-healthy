package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.dao.ProfileDao;
import by.fpmibsu.be_healthy.entity.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileService {
    public List<Profile> getAll() throws SQLException {
        return new ProfileDao().getAll();
    }


    public Profile getEntityById(long id) throws SQLException {
        return new ProfileDao().getEntityById(id);
    }


    public boolean update(Profile entity) throws SQLException {
        return new ProfileDao().update(entity);
    }

    public boolean delete(Profile entity) throws SQLException {
        return new ProfileDao().delete(entity);
    }

    public boolean create(Profile entity) throws SQLException {
        return new ProfileDao().create(entity);
    }
    public boolean isProfileExist(String login, String password) throws SQLException{
        return new ProfileDao().isProfileExist(login, password);
    }
    public boolean register(String login, String password) throws SQLException {
        return new ProfileDao().register(login, password);
    }
    public boolean isLoginAvailable(String login) throws SQLException {
        return new ProfileDao().isLoginAvailable(login);
    }
    public String getAllJSON() throws JsonProcessingException, SQLException {
        return new ObjectMapper().writeValueAsString(new ProfileService().getAll());
    }
    public int getIdByLogin(String login) throws SQLException {
        return new ProfileDao().getIdByLogin(login);
    }
}
