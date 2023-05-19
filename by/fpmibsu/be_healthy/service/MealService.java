package by.fpmibsu.be_healthy.service;

import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.dao.MealDao;
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
        return new MealDao().getAll();
    }


    public Meal getEntityById(long id) {
        logger.debug("Get meal by id");
        return new MealDao().getEntityById(id);
    }


    public boolean update(Meal entity) {
        logger.debug("Update meal");
        return new MealDao().update(entity);
    }

    public boolean delete(int id) {
        logger.debug("Delete meal");
        return new MealDao().delete(id);
    }

    public boolean create(Meal entity) {
        logger.debug("Create meal");
        return new MealDao().create(entity);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all meals in JSON format");
        return new ObjectMapper().writeValueAsString(new MealService().getAll());
    }

    public List<Meal> getAllByDateAndUserId(Date date, int id) {
        logger.debug("Get all user's meals on a specific day");
        return new MealDao().getAllByDateAndUserId(date, id);
    }

    public String getAllByDateAndUserIdJSON(Date date, int id) throws JsonProcessingException {
        logger.debug("Get all user's meals on a specific day in JSON format");
        return new ObjectMapper().writeValueAsString(getAllByDateAndUserId(date, id));
    }
}
