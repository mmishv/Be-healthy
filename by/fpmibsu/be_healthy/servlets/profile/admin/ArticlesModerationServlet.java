package by.fpmibsu.be_healthy.servlets.profile.admin;

import by.fpmibsu.be_healthy.services.ArticleService;
import by.fpmibsu.be_healthy.services.RecipeCategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ArticlesModerationServlet", value = "/articles_moderation/*")
public class ArticlesModerationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ArticlesModerationServlet.class);
    final int RECIPES_PER_PAGE = 6;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to admin article moderation page");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int page = Integer.parseInt(pathParts[pathParts.length-1]);
        int  articles_cnt = new ArticleService().getNumberOfArticles(false);
        var articles = new ArticleService().getPage(page, RECIPES_PER_PAGE, false);
        int page_cnt = (int) (1.0 * (articles_cnt + RECIPES_PER_PAGE - 1)/RECIPES_PER_PAGE);
        request.setAttribute("page_cnt", page_cnt);
        request.setAttribute("cur_page", page);
        request.setAttribute("articles", articles);
        request.setAttribute("categories", new RecipeCategoryService().getAll());
        getServletContext().getRequestDispatcher("/jsp/profile/admin/articles_moderation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition in order to update article moderation status");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String[] lastPart = pathParts[pathParts.length-1].split("-");
        int id = Integer.parseInt(lastPart[lastPart.length-1]);
        new ArticleService().updateModerationStatus(id, true);
        response.sendRedirect(request.getContextPath()+"/articles_moderation/"+Integer.parseInt(lastPart[0]));
    }
}

