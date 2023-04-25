package servlets;

import by.fpmibsu.be_healthy.dao.RecipeDao;
import by.fpmibsu.be_healthy.entity.Recipe;
import jakarta.servlet.annotation.WebServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/recipes")
public class RecipesMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            recipes = (ArrayList<Recipe>) new RecipeDao().getAll();
        } catch (SQLException e) {
            request.setAttribute("error", "Что-то пошло не так...");
        }
        request.setAttribute("recipes", recipes);
        getServletContext().getRequestDispatcher("/jsp/recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
