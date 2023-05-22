package by.fpmibsu.be_healthy.service;

import by.fpmibsu.be_healthy.dao.RoleDao;
import by.fpmibsu.be_healthy.entity.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RoleService {
    private static final Logger logger = LogManager.getLogger(Role.class);
    public List<Role> getAll() {
        logger.debug("Get all user roles");
        return new RoleDao().getAll();
    }

    public Role getEntityById(long id) {
        logger.debug("Get user role by id");
        return new RoleDao().getEntityById(id);
    }

    public boolean update(Role entity) {
        logger.debug("Update user role");
        return new RoleDao().update(entity);
    }

    public boolean delete(int id) {
        logger.debug("Delete user role");
        return new RoleDao().delete(id);
    }

    public boolean create(Role entity) {
        logger.debug("Create user role");
        return new RoleDao().create(entity);
    }
    public Role getRoleByUserId(int id) {
        logger.debug("Get role by user id");
        return new RoleDao().getRoleByUserId(id);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all user roles on JSON format");
        return new ObjectMapper().writeValueAsString(getAll());
    }

    public String getEntityByIdJSON(long id) throws JsonProcessingException {
        logger.debug("Get user role by id in JSON format");
        return new ObjectMapper().writeValueAsString(getEntityById(id));
    }
}