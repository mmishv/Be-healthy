package by.fpmibsu.be_healthy.service;

import by.fpmibsu.be_healthy.dao.MealDao;
import by.fpmibsu.be_healthy.entity.Meal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class MealService {
    private static final Logger logger = LogManager.getLogger(MealService.class);
    public List<Meal> getAll() {
        logger.debug("Get all meals");
        var meals = new MealDao().getAll();
        for (var meal: meals){
            meal.setProducts(new MealProductService().getProductsByMealId(meal.getId()));
        }
        return meals;
    }


    public Meal getEntityById(Integer id) {
        logger.debug("Get meal by id");
        var meal = new MealDao().getEntityById(id);
        if (meal!=null)
             meal.setProducts(new MealProductService().getProductsByMealId(id));
        return meal;
    }


    public boolean update(Meal entity) {
        logger.info("Update meal");
        var res = new MealDao().update(entity);
        if (res){
            new MealProductService().deleteMealProducts(entity.getId());
            for (var i : entity.getProducts()) {
                i.setMealId(entity.getId());
                new MealProductService().create(i);
            }
        }
        return res;
    }

    public boolean delete(int id) {
        logger.warn("Delete meal");
        return new MealDao().delete(id);
    }

    public boolean create(Meal entity) {
        logger.info("Create meal");
        var res = new MealDao().create(entity);
        if (res){
            int id = getMaxId();
            for (var i : entity.getProducts()) {
                i.setMealId(id);
                new MealProductService().create(i);
            }
        }
        return res;
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all meals in JSON format");
        return new ObjectMapper().writeValueAsString(new MealService().getAll());
    }

    public List<Meal> getAllByDateAndUserId(Date date, int id) {
        logger.debug("Get all user's meals on a specific day");
        var meals = new MealDao().getAllByDateAndUserId(date, id);
        for (var meal: meals){
            meal.setProducts(new MealProductService().getProductsByMealId(meal.getId()));
        }
        return meals;
    }

    public String getAllByDateAndUserIdJSON(Date date, int id) throws JsonProcessingException {
        logger.debug("Get all user's meals on a specific day in JSON format");
        return new ObjectMapper().writeValueAsString(getAllByDateAndUserId(date, id));
    }

    public String getEntityByIdJSON(Integer id) throws JsonProcessingException {
        logger.debug("Get meal by id in JSON format");
        return new ObjectMapper().writeValueAsString(getEntityById(id));
    }
    public int getMaxId() {
        return new MealDao().getMaxId();
    }
}
