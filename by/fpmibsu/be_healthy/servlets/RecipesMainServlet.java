package by.fpmibsu.be_healthy.servlets;

import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.services.RecipeCategoryService;
import by.fpmibsu.be_healthy.services.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class RecipesMainServlet extends HttpServlet {
    final int RECIPES_PER_PAGE = 3;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int page = Integer.parseInt(pathParts[pathParts.length-1]);
        int recipe_cnt = 0;
        try {
            recipe_cnt = new RecipeService().getNumberOfRecipes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int page_cnt = (int) (1.0 * (recipe_cnt + RECIPES_PER_PAGE - 1)/RECIPES_PER_PAGE);
        request.setAttribute("page_cnt", page_cnt);
        request.setAttribute("cur_page", page);
        try {
            request.setAttribute("recipes", new RecipeService().getPageJSON(page, RECIPES_PER_PAGE));
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
