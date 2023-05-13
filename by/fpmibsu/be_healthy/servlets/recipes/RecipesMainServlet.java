package by.fpmibsu.be_healthy.servlets.recipes;

import by.fpmibsu.be_healthy.services.ProfileService;
import by.fpmibsu.be_healthy.services.RecipeCategoryService;
import by.fpmibsu.be_healthy.services.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"/recipes/*"})
public class RecipesMainServlet extends HttpServlet {
    final int RECIPES_PER_PAGE = 6;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            authors.add(new ProfileService().getEntityById(r.getAuthorId()).getLogin());
        }
        request.setAttribute("authors", authors);
        getServletContext().getRequestDispatcher("/jsp/recipes/recipe.jsp").forward(request, response);
    }
}
