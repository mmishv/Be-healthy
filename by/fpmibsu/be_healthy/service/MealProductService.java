package by.fpmibsu.be_healthy.service;

import by.fpmibsu.be_healthy.dao.MealProductDao;
import by.fpmibsu.be_healthy.entity.MealProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MealProductService {
    private static final Logger logger = LogManager.getLogger(MealProductService.class);
    public List<MealProduct> getAll() {
        logger.debug("Get all meal products");
        List<MealProduct> products = new java.util.ArrayList<>(List.of());
        for (var full_product : new MealProductDao().getAll()){
            var product = full_product;
            full_product = new MealProduct(new ProductService().getEntityById(product.getId()));
            full_product.setMealProductId(product.getMealProductId());
            full_product.setQuantity(product.getQuantity());
            full_product.setMealId(product.getMealId());
            products.add(full_product);
        }
        return products;
    }


    public MealProduct getEntityById(Integer id) {
        logger.debug("Get meal product by id");
        var product = new MealProductDao().getEntityById(id);
        if (product!=null){
            var full_product = new MealProduct(new ProductService().getEntityById(product.getId()));
            full_product.setMealProductId(product.getMealProductId());
            full_product.setQuantity(product.getQuantity());
            full_product.setMealId(product.getMealId());
            return full_product;
        }
        return null;
    }


    public boolean update(MealProduct entity) {
        logger.info("Update meal product");
        return new MealProductDao().update(entity);
    }

    public boolean deleteMealProducts(int id) {
        logger.trace("Delete meal products in meal by meal id");
        return new MealProductDao().deleteMealProducts(id);
    }

    public boolean delete(int id) {
        logger.warn("Delete meal product");
        return new MealProductDao().delete(id);
    }

    public boolean create(MealProduct entity) {
        logger.info("Create meal product");
        return new MealProductDao().create(entity);
    }

    public List<MealProduct> getProductsByMealId(int id) {
        logger.debug("Get all meal products in meal by meal id");
        List<MealProduct> products = new java.util.ArrayList<>(List.of());
        for (var full_product : new MealProductDao().getProductsByMealId(id)){
            var product = full_product;
            full_product = new MealProduct(new ProductService().getEntityById(product.getId()));
            full_product.setMealProductId(product.getMealProductId());
            full_product.setQuantity(product.getQuantity());
            full_product.setMealId(product.getMealId());
            products.add(full_product);
        }
        return products;
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all meal products in JSON format");
        return new ObjectMapper().writeValueAsString(new MealProductService().getAll());
    }

    public String getProductsByMealIdJSON(int id) throws JsonProcessingException {
        logger.debug("Get all meal products in meal by meal id in JSON format");
        return new ObjectMapper().writeValueAsString(getProductsByMealId(id));
    }

    public String getEntityByIdJSON(Integer id) throws JsonProcessingException {
        logger.debug("Get meal product by id in JSON format");
        return new ObjectMapper().writeValueAsString(getEntityById(id));
    }
}
