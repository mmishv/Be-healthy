package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.MealProduct;
import by.fpmibsu.be_healthy.dao.MealProductDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MealProductService {
    private static final Logger logger = LogManager.getLogger(MealProductService.class);
    public List<MealProduct> getAll() {
        logger.debug("Get all meal products");
        return new MealProductDao().getAll();
    }


    public MealProduct getEntityById(long id) {
        logger.debug("Get meal product by id");
        return new MealProductDao().getEntityById(id);
    }


    public boolean update(MealProduct entity) {
        logger.debug("Update meal product");
        return new MealProductDao().update(entity);
    }

    public boolean deleteMealProducts(int id) {
        logger.debug("Delete meal products in meal by meal id");
        return new MealProductDao().deleteMealProducts(id);
    }

    public boolean delete(int id) {
        logger.debug("Delete meal product");
        return new MealProductDao().delete(id);
    }

    public boolean create(MealProduct entity) {
        logger.debug("Create meal product");
        return new MealProductDao().create(entity);
    }

    public List<MealProduct> getProductsByMealId(int id) {
        logger.debug("Get all meal products in meal by meal id");
        return new MealProductDao().getProductsByMealId(id);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all meal products in JSON format");
        return new ObjectMapper().writeValueAsString(new MealProductService().getAll());
    }
}
