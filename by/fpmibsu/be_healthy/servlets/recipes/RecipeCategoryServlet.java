package by.fpmibsu.be_healthy.servlets.recipes;

import by.fpmibsu.be_healthy.services.RecipeCategoryService;
import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecipeCategoryServlet", value = "/RecipeCategoryServlet")
public class RecipeCategoryServlet extends HttpServlet {
    final int RECIPES_PER_PAGE = 1;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String[] parts = pathParts[pathParts.length-1].split("-");
        int page = Integer.parseInt(parts[parts.length-1]);
        int category_id = Integer.parseInt(parts[parts.length-2]);
        int recipe_cnt = new RecipeService().getNumberOfRecipesInCategory(category_id);
        int page_cnt = (int) (1.0 * (recipe_cnt + RECIPES_PER_PAGE - 1)/RECIPES_PER_PAGE);
        request.setAttribute("page_cnt", page_cnt);
        request.setAttribute("cur_page", page);
        request.setAttribute("cat_id", category_id);
        request.setAttribute("recipes", new RecipeService().getCategoryPageJSON(page, RECIPES_PER_PAGE, category_id));
        request.setAttribute("categories", new RecipeCategoryService().getAllJSON());
        getServletContext().getRequestDispatcher("/jsp/recipes/recipe.jsp").forward(request, response);
    }
}
