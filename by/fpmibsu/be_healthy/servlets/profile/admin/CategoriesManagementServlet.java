package by.fpmibsu.be_healthy.servlets.profile.admin;

import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.services.ArticleCategoryService;
import by.fpmibsu.be_healthy.services.RecipeCategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "CategoriesManagementServlet", value = "/categories_management/*")
public class CategoriesManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        if (pathParts[pathParts.length-1].equals("recipe"))
            request.setAttribute("items", new RecipeCategoryService().getAll());
        else
            request.setAttribute("items", new ArticleCategoryService().getAll());
        getServletContext().getRequestDispatcher("/jsp/profile/admin/admin-categories-products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String name = new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        if (pathParts[pathParts.length - 1].equals("recipe")) {
            RecipeCategory rcat = new RecipeCategory();
            rcat.setName(name);
            new RecipeCategoryService().create(rcat);
            response.sendRedirect(request.getContextPath() + "/categories_management/recipe");
        }
        else{
            ArticleCategory acat = new ArticleCategory();
            acat.setName(name);
            new ArticleCategoryService().create(acat);
            response.sendRedirect(request.getContextPath()+"/categories_management/article");
        }
    }
}
