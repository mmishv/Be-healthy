package by.fpmibsu.be_healthy.dao;

import by.fpmibsu.be_healthy.bl.JDBCPostgreSQL;
import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.entity.MealProduct;

import java.sql.SQLException;
import java.util.List;

public class MealProductDao extends JDBCPostgreSQL implements Dao<MealProduct>{

    @Override
    public List<MealProduct> getAll() throws SQLException {
        return null;
    }

    @Override
    public MealProduct getEntityById(long id) throws SQLException {
        return null;
    }

    @Override
    public MealProduct update(MealProduct entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(MealProduct entity) throws SQLException {

    }

    @Override
    public void create(MealProduct entity) throws SQLException {

    }
}
