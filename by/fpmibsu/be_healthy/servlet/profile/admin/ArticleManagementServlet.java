package by.fpmibsu.be_healthy.servlet.profile.admin;

import by.fpmibsu.be_healthy.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ArticleManagementServlet", value = "/articles_management")
public class ArticleManagementServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ArticleManagementServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Transition to admin article management page");
        request.setAttribute("items", new ArticleService().getAll());
        getServletContext().getRequestDispatcher("/jsp/profile/admin/admin-recipes-articles.jsp").forward(request, response);
    }
}
