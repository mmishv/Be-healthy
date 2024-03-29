package by.fpmibsu.be_healthy.service;

import by.fpmibsu.be_healthy.dao.ProfileDao;
import by.fpmibsu.be_healthy.entity.Profile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProfileService {
    private static final Logger logger = LogManager.getLogger(ProfileService.class);
    public List<Profile> getAll() {
        logger.debug("Get all profiles");
        var profiles = new ProfileDao().getAll();
        for (var profile : profiles){
            profile.setRole(new RoleService().getEntityById(profile.getRole().getId()));
        }
        return profiles;
    }


    public Profile getEntityById(Integer id) {
        logger.debug("Get profile by entity id");
        var profile = new ProfileDao().getEntityById(id);
        if (profile != null)
            profile.setRole(new RoleService().getEntityById(profile.getRole().getId()));
        return profile;
    }


    public boolean update(Profile entity) {
        logger.info("Update profile");
        return new ProfileDao().update(entity);
    }

    public boolean delete(Integer id) {
        logger.warn("Delete profile");
        return new ProfileDao().delete(id);
    }

    public boolean create(Profile entity) {
        logger.info("Create profile");
        return new ProfileDao().create(entity);
    }

    public String getPasswordByLogin(String login) {
        logger.debug("Get profile password by login");
        return new ProfileDao().getPasswordByLogin(login);
    }

    public boolean register(String login, String password) {
        logger.info("Register profile");
        return new ProfileDao().register(login, password);
    }

    public boolean isLoginAvailable(String login) {
        logger.debug("Check is login available");
        return new ProfileDao().isLoginAvailable(login);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all profiles in JSON format");
        return new ObjectMapper().writeValueAsString(new ProfileService().getAll());
    }

    public String getEntityByIdJSON(Integer id) throws JsonProcessingException {
        logger.debug("Get profile by entity id");
        return new ObjectMapper().writeValueAsString(getEntityById(id));
    }


    public int getIdByLogin(String login) {
        logger.debug("Get profile id by login");
        return new ProfileDao().getIdByLogin(login);
    }

    public boolean updateMainInfo(Profile entity) {
        logger.info("Update profile main info");
        return new ProfileDao().updateMainInfo(entity);
    }
    public boolean updateRole(Profile entity){
        logger.info("Update profile role");
        return new ProfileDao().updateRole(entity);
    }
}
