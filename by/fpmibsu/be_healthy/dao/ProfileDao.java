package by.fpmibsu.be_healthy.dao;


import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.entity.Role;
import by.fpmibsu.be_healthy.postgres.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

public class ProfileDao implements Dao<Integer, Profile> {
    private static final Logger logger = LogManager.getLogger(ProfileDao.class);

    @Override
    public List<Profile> getAll() {
        List<Profile> profiles = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PROFILE ORDER BY LOGIN");
            while (resultSet.next()) {
                Profile profile = new Profile();
                initProfile(resultSet, profile);
                profiles.add(profile);
            }
        } catch (SQLException e) {
            logger.error("Error getting all profiles");
            logger.trace(e);
            e.printStackTrace();
        }
        return profiles;
    }

    private void initProfile(ResultSet resultSet, Profile profile) throws SQLException {
        profile.setId(resultSet.getInt("ID"));
        profile.setName(resultSet.getString("NAME"));
        profile.setSex(resultSet.getString("SEX"));
        profile.setEmail(resultSet.getString("EMAIL"));
        profile.setLogin(resultSet.getString("LOGIN"));
        profile.setPassword(resultSet.getString("PASSWORD"));
        profile.setAvatar(resultSet.getBytes("AVATAR"));
        if (profile.getAvatar() != null) {
            byte[] encodeBase64 = Base64.getEncoder().encode(resultSet.getBytes("AVATAR"));
            String base64encoded = new String(encodeBase64, StandardCharsets.UTF_8);
            profile.setBase64image(base64encoded);
        }
        HashMap<String, BigDecimal> KBJU_norm = new HashMap<>();
        KBJU_norm.put("k", BigDecimal.valueOf(resultSet.getDouble("CAL_NORM")).setScale(1, RoundingMode.HALF_UP));
        KBJU_norm.put("b", BigDecimal.valueOf(resultSet.getDouble("PROT_NORM")).setScale(1, RoundingMode.HALF_UP));
        KBJU_norm.put("j", BigDecimal.valueOf(resultSet.getDouble("FATS_NORM")).setScale(1, RoundingMode.HALF_UP));
        KBJU_norm.put("u", BigDecimal.valueOf(resultSet.getDouble("CARB_NORM")).setScale(1, RoundingMode.HALF_UP));
        profile.setKBJU_norm(KBJU_norm);
        Role role = new Role();
        role.setId(resultSet.getInt("ROLE_ID"));
        profile.setRole(role);
        profile.setAge(resultSet.getInt("AGE"));
        profile.setHeight(resultSet.getInt("HEIGHT"));
        profile.setWeight(resultSet.getDouble("WEIGHT"));
        profile.setActivity(resultSet.getDouble("ACTIVITY_COEF"));
        profile.setGoal(resultSet.getDouble("GOAL"));
    }

    @Override
    public Profile getEntityById(Integer id) {
        Profile profile = null;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PROFILE WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile = new Profile();
                initProfile(resultSet, profile);
            }
        } catch (SQLException e) {
            logger.error("Error getting profile by id");
            logger.trace(e);
            e.printStackTrace();
        }
        return profile;
    }

    @Override
    public boolean update(Profile entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE PROFILE SET WEIGHT=?, ACTIVITY_COEF=?,  SEX=?, "
                        + " CAL_NORM=?, CARB_NORM=?, FATS_NORM=?, PROT_NORM=?, GOAL = ?, HEIGHT=?, AGE=? WHERE ID=?")) {
            preparedStatement.setDouble(1, entity.getWeight());
            preparedStatement.setDouble(2, entity.getActivity());
            preparedStatement.setString(3, entity.getSex());
            var norm = entity.getKBJU_norm();
            preparedStatement.setDouble(4, norm.get("k").doubleValue());
            preparedStatement.setDouble(5, norm.get("u").doubleValue());
            preparedStatement.setDouble(6, norm.get("j").doubleValue());
            preparedStatement.setDouble(7, norm.get("b").doubleValue());
            preparedStatement.setDouble(8, entity.getGoal());
            preparedStatement.setInt(9, entity.getHeight());
            preparedStatement.setInt(10, entity.getAge());
            preparedStatement.setInt(11, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error updating profile");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateRole(Profile entity){
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE PROFILE SET ROLE_ID=? WHERE ID=?")) {
            preparedStatement.setInt(1, entity.getRole().getId());
            preparedStatement.setInt(2, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error updating profile role");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateMainInfo(Profile entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE PROFILE SET NAME=?, AVATAR=? WHERE ID=?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBytes(2, entity.getAvatar() != null ? entity.getAvatar() : null);
            preparedStatement.setInt(3, entity.getId());
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error updating profile main info");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM PROFILE WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error deleting profile");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Profile entity) {
        if (entity == null)
            return false;
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO PROFILE (NAME, EMAIL, LOGIN, PASSWORD, AGE, HEIGHT, ACTIVITY_COEF,"
                                + " AVATAR, WEIGHT, CAL_NORM, CARB_NORM, FATS_NORM, PROT_NORM, SEX, GOAL)"
                                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getLogin());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setInt(5, entity.getAge());
            preparedStatement.setInt(6, entity.getHeight());
            preparedStatement.setDouble(7, entity.getActivity());
            preparedStatement.setBytes(8, entity.getAvatar() != null ? entity.getAvatar() : null);
            preparedStatement.setDouble(9, entity.getWeight());
            var norm = entity.getKBJU_norm();
            preparedStatement.setDouble(10, norm.get("k").doubleValue());
            preparedStatement.setDouble(11, norm.get("u").doubleValue());
            preparedStatement.setDouble(12, norm.get("j").doubleValue());
            preparedStatement.setDouble(13, norm.get("b").doubleValue());
            preparedStatement.setString(14, entity.getSex());
            preparedStatement.setDouble(15, entity.getGoal());
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error creating profile");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getPasswordByLogin(String login) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT PASSWORD FROM PROFILE WHERE LOGIN=?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("PASSWORD");
            }
        } catch (SQLException e) {
            logger.error("Error getting profile password");
            logger.trace(e);
            e.printStackTrace();
        }
        return null;
    }

    public boolean isLoginAvailable(String login) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM PROFILE WHERE LOGIN=?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return false;
        } catch (SQLException e) {
            logger.error("Error checking is login for profile available");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean register(String login, String password) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO PROFILE (LOGIN, PASSWORD) VALUES(?, ?)")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            if (preparedStatement.executeUpdate()==0)
                return false;
        } catch (SQLException e) {
            logger.error("Error during profile registration");
            logger.trace(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getIdByLogin(String login) {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM PROFILE WHERE LOGIN=?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            logger.error("Error getting profile id by login");
            logger.trace(e);
            e.printStackTrace();
        }
        return -1;
    }
}
