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
        new RecipeService().delete(Integer.parseInt(pathParts[pathParts.length-1].split("_")[0]));
        var t = pathParts[pathParts.length-1].split("_");
        if (t.length==1)
            response.sendRedirect("http://localhost:8081/my_recipes/1");
        else if (t.length==2)
            response.sendRedirect("http://localhost:8081/recipes_management");
        else
            response.sendRedirect("http://localhost:8081/recipes_moderation/"+Integer.parseInt(t[t.length-1]));
    }

}
