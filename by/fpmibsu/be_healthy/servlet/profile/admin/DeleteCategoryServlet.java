package by.fpmibsu.be_healthy.servlet.profile.admin;

import by.fpmibsu.be_healthy.service.ArticleCategoryService;
import by.fpmibsu.be_healthy.service.RecipeCategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteCategoryServlet", value = "/delete_category/*")
public class DeleteCategoryServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteCategoryServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.warn("Admin transition in order to delete category");
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
