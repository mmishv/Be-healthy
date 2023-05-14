package by.fpmibsu.be_healthy.servlets.profile.admin;

import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.services.ArticleCategoryService;
import by.fpmibsu.be_healthy.services.ArticleService;
import by.fpmibsu.be_healthy.services.RecipeCategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet({"/edit_category/*"})
public class EditCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int id =  Integer.parseInt(pathParts[pathParts.length-1].split("-")[1]);
        String name = new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        if (pathParts[pathParts.length-1].split("-")[0].equals("r")){
            RecipeCategory rcat = new RecipeCategory();
            rcat.setId(id);
            rcat.setName(name);
            new RecipeCategoryService().update(rcat);
            response.sendRedirect(request.getContextPath()+"/categories_management/recipe");
        }
        else {
            ArticleCategory acat = new ArticleCategory();
            acat.setId(id);
            acat.setName(name);
            new ArticleCategoryService().update(acat);
            response.sendRedirect(request.getContextPath()+"/categories_management/article");
        }
    }
}