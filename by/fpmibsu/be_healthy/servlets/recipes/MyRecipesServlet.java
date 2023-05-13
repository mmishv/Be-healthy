package by.fpmibsu.be_healthy.servlets.recipes;

import by.fpmibsu.be_healthy.services.ProfileService;
import by.fpmibsu.be_healthy.services.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MyRecipesServlet", value = "/my_recipes/*")
public class MyRecipesServlet extends HttpServlet {
    final int RECIPES_PER_PAGE = 2;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
