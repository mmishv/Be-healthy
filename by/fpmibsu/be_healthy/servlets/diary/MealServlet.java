package by.fpmibsu.be_healthy.servlets.diary;

import by.fpmibsu.be_healthy.dao.MealDao;
import by.fpmibsu.be_healthy.entity.*;
import by.fpmibsu.be_healthy.services.MealService;
import by.fpmibsu.be_healthy.services.ProductService;
import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.valueOf;

@WebServlet(name = "AddMealServlet", value = "/AddMealServlet")
public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int user_id =  (int) request.getSession().getAttribute("id");
        try {
            request.setAttribute("meals", new MealService().getAllByDateAndUserIdJSON(
                    valueOf(pathParts[pathParts.length-1]), user_id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Profile cur_user = new ProfileService().getEntityById(user_id);
            var kbju_norm = cur_user.getKBJU_norm();
            var kbju =  new MealService().getKBJUByDateAndUserId(valueOf(pathParts[pathParts.length-1]), user_id);
            request.setAttribute("k", kbju.get("k"));
            request.setAttribute("b", kbju.get("b"));
            request.setAttribute("j", kbju.get("j"));
            request.setAttribute("u", kbju.get("u"));
            request.setAttribute("k_norm", kbju_norm.get("k"));
            request.setAttribute("b_norm", kbju_norm.get("b"));
            request.setAttribute("j_norm", kbju_norm.get("j"));
            request.setAttribute("u_norm", kbju_norm.get("u"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            request.setAttribute("products", new ProductService().getAllJSON());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("date", pathParts[pathParts.length-1]);
        getServletContext().getRequestDispatcher("/jsp/diary/diary.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealProduct> products = new ArrayList<>();
        String title =  new String(request.getParameter("title").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int cnt = 1;
        while (request.getParameter("product" + cnt) != null) {
            MealProduct product = new MealProduct();
            product.setId(Integer.parseInt(request.getParameter("product" + cnt)));
            product.setQuantity(Integer.parseInt(request.getParameter("quantity" + cnt)));
            products.add(product);
            cnt++;
        }
        int user_id = (int) request.getSession().getAttribute("id");
        Meal meal = new Meal();
        meal.setProducts(products);
        meal.setUser_id(user_id);
        meal.setName(title);
        meal.setDateOfMeal(valueOf(pathParts[pathParts.length-1]));
        meal.setTimeOfMeal(Time.valueOf(LocalTime.now()));
        try {
            new MealDao().create(meal);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("http://localhost:8081/diary/"+pathParts[pathParts.length-1]);
    }
}
