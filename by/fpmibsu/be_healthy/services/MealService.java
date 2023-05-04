package by.fpmibsu.be_healthy.services;

import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.dao.MealDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MealService {
    public List<Meal> getAll() throws SQLException {
        return new MealDao().getAll();
    }


    public Meal getEntityById(long id) throws SQLException {
        return new MealDao().getEntityById(id);
    }


    public boolean update(Meal entity) throws SQLException {
        return new MealDao().update(entity);
    }

    public boolean delete(Meal entity) throws SQLException {
        return new MealDao().delete(entity);
    }

    public boolean create(Meal entity) throws SQLException {
        return new MealDao().create(entity);
    }

    public List<Meal> getMealsByUserId(int id) throws SQLException {
        return new MealDao().getMealsByUserId(id);
    }

    public String getAllJSON() throws JsonProcessingException, SQLException {
        return new ObjectMapper().writeValueAsString(new MealService().getAll());
    }
    public List<Meal> getAllByDateAndUserId(Date date, int id) throws SQLException {
        return new MealDao().getAllByDateAndUserId(date, id);
    }
    public String getAllByDateAndUserIdJSON(Date date, int id) throws SQLException, JsonProcessingException {
        return new ObjectMapper().writeValueAsString(getAllByDateAndUserId(date, id));
    }

    public HashMap<String, BigDecimal> getKBJUByDateAndUserId(Date date, int id) throws SQLException {
        HashMap<String, BigDecimal> kbju = new HashMap<>();
        kbju.put("k", BigDecimal.valueOf(0));
        kbju.put("b", BigDecimal.valueOf(0));
        kbju.put("j", BigDecimal.valueOf(0));
        kbju.put("u", BigDecimal.valueOf(0));
        for (var i: new MealDao().getAllByDateAndUserId(date, id)){
            HashMap<String, BigDecimal> t = i.getKBJU();
            kbju.replace("k", kbju.get("k").add(t.get("k")));
            kbju.replace("b", kbju.get("b").add(t.get("b")));
            kbju.replace("j", kbju.get("j").add(t.get("j")));
            kbju.replace("u", kbju.get("u").add(t.get("u")));
        }
        return kbju;
    }
}
