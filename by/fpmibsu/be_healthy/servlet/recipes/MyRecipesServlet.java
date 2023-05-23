package by.fpmibsu.be_healthy.servlet.recipes;

import by.fpmibsu.be_healthy.service.ProfileService;
import by.fpmibsu.be_healthy.service.RecipeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MyRecipesServlet", value = "/my_recipes/*")
public class MyRecipesServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MyRecipesServlet.class);
    final int RECIPES_PER_PAGE = 6;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to user's recipes page");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int page = Integer.parseInt(pathParts[pathParts.length-1]);
        int recipe_cnt = new RecipeService().getNumberOfRecipesWrittenBy((int) request.getSession().getAttribute("id"));
        int page_cnt = (int) (1.0 * (recipe_cnt + RECIPES_PER_PAGE - 1)/RECIPES_PER_PAGE);
        request.setAttribute("page_cnt", page_cnt);
        request.setAttribute("cur_page", page);
        var recipes =  new RecipeService().getAuthorPage(page, RECIPES_PER_PAGE,
                (int) request.getSession().getAttribute("id"));
        request.setAttribute("recipes", recipes);
        ArrayList<String> authors = new ArrayList<>();
        for (var r : recipes){
            authors.add(new ProfileService().getEntityById(r.getAuthorId()).getLogin());
        }
        request.setAttribute("authors", authors);
        getServletContext().getRequestDispatcher("/jsp/recipes/my_recipes.jsp").forward(request, response);
    }
}
