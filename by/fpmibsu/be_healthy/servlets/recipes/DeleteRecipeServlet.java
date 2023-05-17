package by.fpmibsu.be_healthy.servlets.recipes;

import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.services.RecipeService;
import by.fpmibsu.be_healthy.servlets.profile.articles.CreateArticleServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeServlet", value = "/delete_recipe/*")
public class DeleteRecipeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteRecipeServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition in order to delete recipe");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        new RecipeService().delete(Integer.parseInt(pathParts[pathParts.length-1].split("_")[0]));
        var t = pathParts[pathParts.length-1].split("_");
        if (t.length==1)
            response.sendRedirect(request.getContextPath()+"/my_recipes/1");
        else if (t.length==2)
            response.sendRedirect(request.getContextPath()+"/recipes_management");
        else
            response.sendRedirect(request.getContextPath()+"/recipes_moderation/"+Integer.parseInt(t[t.length-1]));
    }
}
