package by.fpmibsu.be_healthy.servlets.profile.admin;

import by.fpmibsu.be_healthy.services.RecipeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecipeManagementServlet", value = "/recipes_management")
public class RecipeManagementServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RecipeManagementServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to admin recipe management page");
        request.setAttribute("items", new RecipeService().getAll());
        getServletContext().getRequestDispatcher("/jsp/profile/admin/admin-recipes-articles.jsp").forward(request, response);
    }
}
