package by.fpmibsu.be_healthy.service;

import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.dao.ProductDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductService {
    private static final Logger logger = LogManager.getLogger(ProductService.class);
    public List<Product> getAll() {
        logger.debug("Get all products");
        return new ProductDao().getAll();
    }


    public Product getEntityById(long id) {
        logger.debug("Get product by id");
        return new ProductDao().getEntityById(id);
    }


    public boolean update(Product entity) {
        logger.debug("Update product");
        return new ProductDao().update(entity);
    }

    public boolean delete(int id) {
        logger.debug("Delete product");
        return new ProductDao().delete(id);
    }

    public boolean create(Product entity) {
        logger.debug("Create product");
        return new ProductDao().create(entity);
    }

    public String getAllJSON() throws JsonProcessingException {
        logger.debug("Get all products in JSON format");
        return new ObjectMapper().writeValueAsString(new ProductService().getAll());
    }
}