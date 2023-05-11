package by.fpmibsu.be_healthy.servlets.recipes;

import by.fpmibsu.be_healthy.dao.MealDao;
import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteRecipeServlet", value = "/DeleteRecipeServlet")
public class DeleteRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Recipe recipe = new Recipe();
        recipe.setId(Integer.parseInt(pathParts[pathParts.length-1]));
        new RecipeService().delete(recipe);
        response.sendRedirect("http://localhost:8081/my_recipes/1");
    }

}
