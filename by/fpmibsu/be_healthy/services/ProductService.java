package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.dao.ProductDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public List<Product> getAll() throws SQLException {
        return new ProductDao().getAll();
    }


    public Product getEntityById(long id) throws SQLException {
        return new ProductDao().getEntityById(id);
    }


    public boolean update(Product entity) throws SQLException {
        return new ProductDao().update(entity);
    }

    public boolean delete(Product entity) throws SQLException {
        return new ProductDao().delete(entity);
    }

    public boolean create(Product entity) throws SQLException {
        return new ProductDao().create(entity);
    }

    public String getAllJSON() throws JsonProcessingException, SQLException {
        return new ObjectMapper().writeValueAsString(new ProductService().getAll());
    }
}
