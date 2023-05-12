package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.dao.ProfileDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ProfileService {
    public List<Profile> getAll() {
        return new ProfileDao().getAll();
    }


    public Profile getEntityById(long id) {
        return new ProfileDao().getEntityById(id);
    }


    public boolean update(Profile entity) {
        return new ProfileDao().update(entity);
    }

    public boolean delete(Profile entity) {
        return new ProfileDao().delete(entity);
    }

    public boolean create(Profile entity) {
        return new ProfileDao().create(entity);
    }

    public String getPasswordByLogin(String login) {
        return new ProfileDao().getPasswordByLogin(login);
    }

    public boolean register(String login, String password) {
        return new ProfileDao().register(login, password);
    }

    public boolean isLoginAvailable(String login) {
        return new ProfileDao().isLoginAvailable(login);
    }

    public String getAllJSON() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new ProfileService().getAll());
    }

    public int getIdByLogin(String login) {
        return new ProfileDao().getIdByLogin(login);
    }

    public boolean updateMainInfo(Profile entity) {
        return new ProfileDao().updateMainInfo(entity);
    }
    public boolean updateRole(Profile entity){
        return new ProfileDao().updateRole(entity);
    }
}
