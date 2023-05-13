package by.fpmibsu.be_healthy.servlets.recipes;

import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
        request.setAttribute("recipes", new RecipeService().getAuthorPageJSON(page, RECIPES_PER_PAGE,
                (int) request.getSession().getAttribute("id")));
        getServletContext().getRequestDispatcher("/jsp/recipes/my_recipes.jsp").forward(request, response);
    }
}
