package by.fpmibsu.be_healthy.servlets;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.services.IngredientService;
import by.fpmibsu.be_healthy.services.ProductService;
import by.fpmibsu.be_healthy.services.RecipeCategoryService;
import by.fpmibsu.be_healthy.services.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "createRecipeServlet", value = "/createRecipeServlet")
public class CreateRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("products", new ProductService().getAllJSON());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            request.setAttribute("categories", new RecipeCategoryService().getAllJSON());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/jsp/new_recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
