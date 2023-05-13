package by.fpmibsu.be_healthy.servlets.diary;

import by.fpmibsu.be_healthy.dao.MealDao;
import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.entity.MealProduct;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditMealServlet", value = "/edit-meal/*")
public class EditMealServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealProduct> products = new ArrayList<>();
        String title =  new String(request.getParameter("title").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String[] part = pathParts[pathParts.length-1].split("_");
        int cnt = 1, meal_id=Integer.parseInt(part[0]);
        while (request.getParameter("add_product"+meal_id+"_" + cnt) != null) {
            MealProduct product = new MealProduct();
            product.setId(Integer.parseInt(request.getParameter("add_product"+meal_id+"_"  + cnt)));
            product.setQuantity(Integer.parseInt(request.getParameter("add_quantity"+meal_id+"_"  + cnt)));
            products.add(product);
            cnt++;
        }
        Meal meal = new Meal();
        meal.setId(meal_id);
        meal.setProducts(products);
        meal.setName(title);
        meal.setTimeOfMeal(Time.valueOf(LocalTime.now()));
        new MealDao().update(meal);
        response.sendRedirect("http://localhost:8081/diary/"+part[1]);
    }
}

