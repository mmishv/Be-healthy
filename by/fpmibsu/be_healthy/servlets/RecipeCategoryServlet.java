package by.fpmibsu.be_healthy.servlets;

import by.fpmibsu.be_healthy.services.RecipeCategoryService;
import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RecipeCategoryServlet", value = "/RecipeCategoryServlet")
public class RecipeCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int category_id = Integer.parseInt(pathParts[pathParts.length-1]);
        try{
            request.setAttribute("recipes", new RecipeService().getAllInCategoryJSON(category_id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            request.setAttribute("categories", new RecipeCategoryService().getAllJSON());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/jsp/recipe.jsp").forward(request, response);
    }
}
