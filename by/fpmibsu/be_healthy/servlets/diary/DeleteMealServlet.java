package by.fpmibsu.be_healthy.servlets.diary;

import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.services.MealService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteMealServlet", value = "/delete-meal/*")
public class DeleteMealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String[] part =pathParts[pathParts.length-1].split("_");
        new MealService().delete(Integer.parseInt(part[0]));
        response.sendRedirect(request.getContextPath()+"/diary/"+part[1]);
    }
}
