package by.fpmibsu.be_healthy.servlets.profile.admin;

import by.fpmibsu.be_healthy.services.ArticleCategoryService;
import by.fpmibsu.be_healthy.services.RecipeCategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteCategoryServlet", value = "/delete_category/*")
public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int id =  Integer.parseInt(pathParts[pathParts.length-1].split("-")[1]);
        if (pathParts[pathParts.length-1].split("-")[0].equals("r")){
            new RecipeCategoryService().delete(id);
            response.sendRedirect(request.getContextPath()+"/categories_management/recipe");
        }
        else {
            new ArticleCategoryService().delete(id);
            response.sendRedirect(request.getContextPath()+"/categories_management/article");
        }
    }
}
