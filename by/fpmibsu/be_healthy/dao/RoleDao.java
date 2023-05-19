package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.entity.Role;
import by.fpmibsu.be_healthy.postgres.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDao implements Dao<Role> {
    private static final Logger logger = LogManager.getLogger(ProfileDao.class);

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = DataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME FROM ROLE ORDER BY NAME");
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("ID"));
                role.setName(resultSet.getString("NAME"));
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.error("Error getting all user roles");
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role getEntityById(long id) {
        Role role = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME FROM ROLE WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getInt("ID"));
                role.setName(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            logger.error("Error getting user role by id");
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public boolean update(Role entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ROLE SET NAME=? WHERE ID=?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error updating user role");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ROLE WHERE ID=?");) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error deleting user role");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Role entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ROLE (NAME) VALUES(?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating user role");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Role getRoleByUserId(int id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT ROLE_ID AS ID FROM PROFILE WHERE ID=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new RoleDao().getEntityById(resultSet.getInt("ID"));
            }
        } catch (SQLException e) {
            logger.error("Error getting user role by user id");
            e.printStackTrace();
        }
        return null;
    }
}