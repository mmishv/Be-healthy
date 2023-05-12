package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.dao.RoleDao;
import by.fpmibsu.be_healthy.entity.Role;

import java.util.List;

public class RoleService {
    public List<Role> getAll() {
        return new RoleDao().getAll();
    }

    public Role getEntityById(long id) {
        return new RoleDao().getEntityById(id);
    }

    public boolean update(Role entity) {
        return new RoleDao().update(entity);
    }

    public boolean delete(Role entity) {
        return new RoleDao().delete(entity);
    }

    public boolean create(Role entity) {
        return new RoleDao().create(entity);
    }
    public Role getRoleByUserId(int id) {
        return new RoleDao().getRoleByUserId(id);
    }
}