package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.dao.MealDao;
import by.fpmibsu.be_healthy.entity.MealProduct;
import by.fpmibsu.be_healthy.dao.MealProductDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class MealProductService {
    public List<MealProduct> getAll() throws SQLException {
        return new MealProductDao().getAll();
    }


    public MealProduct getEntityById(long id) throws SQLException {
        return new MealProductDao().getEntityById(id);
    }


    public boolean update(MealProduct entity) throws SQLException {
        return new MealProductDao().update(entity);
    }
    public boolean deleteMealProducts(int id) throws SQLException {
        return new MealProductDao().deleteMealProducts(id);
    }
    public boolean delete(MealProduct entity) throws SQLException {
        return new MealProductDao().delete(entity);
    }

    public boolean create(MealProduct entity) throws SQLException {
        return new MealProductDao().create(entity);
    }

    public List<MealProduct> getProductsByMealId(int id) throws SQLException {
        return new MealProductDao().getProductsByMealId(id);
    }

    public String getAllJSON() throws JsonProcessingException, SQLException {
        return new ObjectMapper().writeValueAsString(new MealProductService().getAll());
    }
}
