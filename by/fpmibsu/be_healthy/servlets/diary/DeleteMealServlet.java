package by.fpmibsu.be_healthy.servlets.diary;

import by.fpmibsu.be_healthy.dao.MealDao;
import by.fpmibsu.be_healthy.entity.Meal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteMealServlet", value = "/DeleteMealServlet")
public class DeleteMealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String[] part =pathParts[pathParts.length-1].split("_");
        Meal meal = new Meal();
        meal.setId(Integer.parseInt(part[0]));
        try {
            new MealDao().delete(meal);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("http://localhost:8081/diary/"+part[1]);
    }
}
