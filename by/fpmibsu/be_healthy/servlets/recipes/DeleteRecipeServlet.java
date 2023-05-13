package by.fpmibsu.be_healthy.servlets.recipes;

import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeServlet", value = "/delete_recipe/*")
public class DeleteRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Recipe recipe = new Recipe();
        recipe.setId(Integer.parseInt(pathParts[pathParts.length-1].split("_")[0]));
        new RecipeService().delete(recipe);
        if (pathParts[pathParts.length-1].split("_").length==1)
            response.sendRedirect("http://localhost:8081/my_recipes/1");
        else
            response.sendRedirect("http://localhost:8081/recipes_management");
    }

}
