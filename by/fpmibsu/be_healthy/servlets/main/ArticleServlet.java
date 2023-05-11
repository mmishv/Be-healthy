package by.fpmibsu.be_healthy.servlets.main;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.services.ArticleService;
import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ArticleServlet", value = "/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Article article = new ArticleService().
                getEntityById(Integer.parseInt(pathParts[pathParts.length-1]));
        request.setAttribute("article", article);
        request.setAttribute("author", new ProfileService().getEntityById(article.getAuthorId()).getLogin());
        getServletContext().getRequestDispatcher("/jsp/main/article.jsp").forward(request, response);
    }
}
