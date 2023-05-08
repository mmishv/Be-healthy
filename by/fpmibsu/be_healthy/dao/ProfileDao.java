package by.fpmibsu.be_healthy.dao;


import by.fpmibsu.be_healthy.entity.*;
import by.fpmibsu.be_healthy.pg.JDBCPostgreSQL;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

public class ProfileDao extends JDBCPostgreSQL implements Dao<Profile> {
    private Connection connection = getConnection();

    @Override
    public List<Profile> getAll() throws SQLException {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT * FROM PROFILE";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Profile profile = new Profile();
                initProfile(resultSet, profile);
                profiles.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
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

        if (profile.getAvatar()!=null){
            byte[] encodeBase64 = Base64.getEncoder().encode(resultSet.getBytes("AVATAR"));
            String base64encoded = new String(encodeBase64, StandardCharsets.UTF_8);
            profile.setBase64image(base64encoded);
        }

        HashMap<String, BigDecimal> KBJU_norm = new HashMap<>();
        KBJU_norm.put("k", BigDecimal.valueOf(resultSet.getDouble("CAL_NORM")).setScale(1, RoundingMode.HALF_UP));
        KBJU_norm.put("b", BigDecimal.valueOf(resultSet.getDouble("PROT_NORM")).setScale(1, RoundingMode.HALF_UP));
        KBJU_norm.put("j",BigDecimal.valueOf(resultSet.getDouble("FATS_NORM")).setScale(1, RoundingMode.HALF_UP));
        KBJU_norm.put("u", BigDecimal.valueOf(resultSet.getDouble("CARB_NORM")).setScale(1, RoundingMode.HALF_UP));
        profile.setKBJU_norm(KBJU_norm);
        profile.setAge(resultSet.getInt("AGE"));
        profile.setHeight(resultSet.getInt("HEIGHT"));
        profile.setWeight(resultSet.getDouble("WEIGHT"));
        profile.setActivity(resultSet.getDouble("ACTIVITY_COEF"));
        profile.setGoal(resultSet.getDouble("GOAL"));
    }

    @Override
    public Profile getEntityById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM PROFILE WHERE ID=?";
        Profile profile = new Profile();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                initProfile(resultSet, profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return profile;
    }

    @Override
    public boolean update(Profile entity) throws SQLException {
        boolean success = true;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE PROFILE SET WEIGHT=?, ACTIVITY_COEF=?,  SEX=?, " +
                " CAL_NORM=?, CARB_NORM=?, FATS_NORM=?, PROT_NORM=?, GOAL = ?, HEIGHT=?, AGE=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
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
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }

    public boolean updateMainInfo(Profile entity) throws SQLException {
        boolean success = true;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE PROFILE SET NAME=?, AVATAR=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBytes(2,
                    entity.getAvatar() != null ? entity.getAvatar() : null);
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }

    @Override
    public boolean delete(Profile entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM PROFILE WHERE ID=?";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }

    @Override
    public boolean create(Profile entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO PROFILE (LOGIN, PASSWORD, AGE, HEIGHT, ACTIVITY_COEF," +
                " AVATAR, WEIGHT, CAL_NORM, CARB_NORM, FATS_NORM, PROT_NORM, SEX, GOAL)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getLogin());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setInt(5, entity.getAge());
            preparedStatement.setInt(6, entity.getHeight());
            preparedStatement.setDouble(7, entity.getActivity());
            preparedStatement.setBytes(8,
                    entity.getAvatar() != null ? entity.getAvatar() : null);
            preparedStatement.setDouble(9, entity.getWeight());

            var norm = entity.getKBJU_norm();
            preparedStatement.setDouble(10, norm.get("c").doubleValue());
            preparedStatement.setDouble(11, norm.get("u").doubleValue());
            preparedStatement.setDouble(12, norm.get("j").doubleValue());
            preparedStatement.setDouble(13, norm.get("b").doubleValue());
            preparedStatement.setDouble(13, entity.getGoal());
            preparedStatement.setString(14, entity.getSex());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }

    public boolean isProfileExist(String login, String password) throws SQLException {

        String sql = "SELECT * FROM PROFILE WHERE LOGIN=? AND PASSWORD=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }
    public boolean isLoginAvailable(String login) throws SQLException {
        String sql = "SELECT * FROM PROFILE WHERE LOGIN=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }
    public boolean register(String login, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO PROFILE (LOGIN, PASSWORD) VALUES(?, ?)";
        boolean success = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }

    public int getIdByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM PROFILE WHERE LOGIN=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return -1;
    }
}
