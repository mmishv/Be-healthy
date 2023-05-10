package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.services.ArticleService;
import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteArticleServlet", value = "/DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Article article = new Article();
        article.setId(Integer.parseInt(pathParts[pathParts.length-1]));
        try {
            new ArticleService().delete(article);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("http://localhost:8081/my_articles/1");
    }
}
