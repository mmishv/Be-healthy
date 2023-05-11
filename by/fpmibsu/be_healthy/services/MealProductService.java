package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.MealProduct;
import by.fpmibsu.be_healthy.dao.MealProductDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class MealProductService {
    public List<MealProduct> getAll() {
        return new MealProductDao().getAll();
    }


    public MealProduct getEntityById(long id) {
        return new MealProductDao().getEntityById(id);
    }


    public boolean update(MealProduct entity) {
        return new MealProductDao().update(entity);
    }

    public boolean deleteMealProducts(int id) {
        return new MealProductDao().deleteMealProducts(id);
    }

    public boolean delete(MealProduct entity) {
        return new MealProductDao().delete(entity);
    }

    public boolean create(MealProduct entity) {
        return new MealProductDao().create(entity);
    }

    public List<MealProduct> getProductsByMealId(int id) {
        return new MealProductDao().getProductsByMealId(id);
    }

    public String getAllJSON() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new MealProductService().getAll());
    }
}
