package by.fpmibsu.be_healthy.servlets.profile.articles;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.services.ArticleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteArticleServlet", value = "/delete_article/*")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        new ArticleService().delete(Integer.parseInt(pathParts[pathParts.length-1].split("_")[0]));
        if (pathParts[pathParts.length-1].split("_").length==1)
            response.sendRedirect(request.getContextPath()+"/my_articles/1");
        else
            response.sendRedirect(request.getContextPath()+"/articles_management");
    }
}
