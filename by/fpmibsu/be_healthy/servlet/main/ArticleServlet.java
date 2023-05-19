package by.fpmibsu.be_healthy.servlet.main;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.service.ArticleService;
import by.fpmibsu.be_healthy.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ArticleServlet", value = "/article/*")
public class ArticleServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ArticleServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Open article");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Article article = new ArticleService().
                getEntityById(Integer.parseInt(pathParts[pathParts.length-1]));
        request.setAttribute("article", article);
        request.setAttribute("author", new ProfileService().getEntityById(article.getAuthorId()).getLogin());
        getServletContext().getRequestDispatcher("/jsp/main/article.jsp").forward(request, response);
    }
}
