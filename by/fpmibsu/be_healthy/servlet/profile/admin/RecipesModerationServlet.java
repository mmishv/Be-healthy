package by.fpmibsu.be_healthy.servlet.profile.admin;

import by.fpmibsu.be_healthy.service.ProfileService;
import by.fpmibsu.be_healthy.service.RecipeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RecipesModerationServlet", value = "/recipes_moderation/*")
public class RecipesModerationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RecipesModerationServlet.class);
    final int RECIPES_PER_PAGE = 6;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to admin recipe moderation page");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int page = Integer.parseInt(pathParts[pathParts.length-1]);
        int  recipe_cnt = new RecipeService().getNumberOfRecipes(false);
        var recipes = new RecipeService().getPage(page, RECIPES_PER_PAGE, false);
        int page_cnt = (int) (1.0 * (recipe_cnt + RECIPES_PER_PAGE - 1)/RECIPES_PER_PAGE);
        request.setAttribute("page_cnt", page_cnt);
        request.setAttribute("cur_page", page);
        request.setAttribute("recipes", recipes);
        ArrayList<String> authors = new ArrayList<>();
        for (var r : recipes){
            authors.add(new ProfileService().getEntityById(r.getAuthorId()).getLogin());
        }
        request.setAttribute("authors", authors);
        getServletContext().getRequestDispatcher("/jsp/profile/admin/recipes_moderation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Change recipe moderation status");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String[] lastPart = pathParts[pathParts.length-1].split("-");
        int id = Integer.parseInt(lastPart[lastPart.length-1]);
        new RecipeService().updateModerationStatus(id, true);
        response.sendRedirect(request.getContextPath()+"/recipes_moderation/"+Integer.parseInt(lastPart[0]));
    }
}
