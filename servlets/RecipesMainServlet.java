package servlets;

import by.fpmibsu.be_healthy.entity.Recipe;
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
        ArrayList<Recipe> recipes = new ArrayList<>();
        request.setAttribute("error", "");
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
        getServletContext().getRequestDispatcher("/jsp/recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
