package by.fpmibsu.be_healthy.servlet.recipes;

import by.fpmibsu.be_healthy.service.ProfileService;
import by.fpmibsu.be_healthy.service.RecipeCategoryService;
import by.fpmibsu.be_healthy.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"/recipes/*"})
public class RecipesMainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RecipesMainServlet.class);
    final int RECIPES_PER_PAGE = 6;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to main recipes page");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int page = Integer.parseInt(pathParts[pathParts.length-1]);
        int  recipe_cnt = new RecipeService().getNumberOfRecipes(true);
        int page_cnt = (int) (1.0 * (recipe_cnt + RECIPES_PER_PAGE - 1)/RECIPES_PER_PAGE);
        request.setAttribute("page_cnt", page_cnt);
        request.setAttribute("cur_page", page);
        var recipes =  new RecipeService().getPage(page, RECIPES_PER_PAGE, true);
        request.setAttribute("recipes",new ObjectMapper().writeValueAsString(recipes));
        request.setAttribute("categories", new RecipeCategoryService().getAllJSON());
        ArrayList<String> authors = new ArrayList<>();
        for (var r : recipes){
            authors.add(r==null? null : new ProfileService().getEntityById(r.getAuthorId()).getLogin());
        }
        request.setAttribute("authors", authors);
        getServletContext().getRequestDispatcher("/jsp/recipes/recipe.jsp").forward(request, response);
    }
}
