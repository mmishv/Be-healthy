package servlets;

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", "");
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            recipes = (ArrayList<Recipe>) new RecipeService().getAll();
        } catch (SQLException e) {
            request.setAttribute("error", "Что-то пошло не так...");
        }
        ArrayList<String> json_rec = new ArrayList<>();
        for (var r: recipes){
            json_rec.add(new ObjectMapper().writeValueAsString(r));
        }
        request.setAttribute("recipes", json_rec);

        ArrayList<RecipeCategory> categories = new ArrayList<>();
        try {
            categories = (ArrayList<RecipeCategory>) new RecipeCategoryService().getAll();
        } catch (SQLException e) {
            request.setAttribute("error", "Что-то пошло не так...");
        }
        ArrayList<String> json_cat = new ArrayList<>();
        for (var c: categories){
            json_cat.add(new ObjectMapper().writeValueAsString(c));
        }
        request.setAttribute("recipes", json_rec);
        request.setAttribute("categories", json_cat);
        getServletContext().getRequestDispatcher("/jsp/recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
