package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.dao.MealDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.util.List;

public class MealService {
    public List<Meal> getAll() {
        return new MealDao().getAll();
    }


    public Meal getEntityById(long id) {
        return new MealDao().getEntityById(id);
    }


    public boolean update(Meal entity) {
        return new MealDao().update(entity);
    }

    public boolean delete(int id) {
        return new MealDao().delete(id);
    }

    public boolean create(Meal entity) {
        return new MealDao().create(entity);
    }

    public String getAllJSON() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new MealService().getAll());
    }

    public List<Meal> getAllByDateAndUserId(Date date, int id) {
        return new MealDao().getAllByDateAndUserId(date, id);
    }

    public String getAllByDateAndUserIdJSON(Date date, int id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(getAllByDateAndUserId(date, id));
    }
}
