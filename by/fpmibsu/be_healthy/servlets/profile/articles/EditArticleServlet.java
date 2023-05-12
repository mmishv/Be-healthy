package by.fpmibsu.be_healthy.servlets.profile.articles;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.entity.ArticleCategory;
import by.fpmibsu.be_healthy.services.ArticleCategoryService;
import by.fpmibsu.be_healthy.services.ArticleService;
import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.sql.Date.valueOf;

@WebServlet(name = "EditArticleServlet", value = "/EditArticleServlet")
public class EditArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");;
        int article_id = Integer.parseInt(pathParts[pathParts.length-1]);
        Article article = new ArticleService().getEntityById(article_id);
        request.setAttribute("categories", new ArticleCategoryService().getAll());
        request.setAttribute("a_cats", article.getCategories().
                stream().map(ArticleCategory::getId).collect(Collectors.toList()));
        request.setAttribute("article", article);
        getServletContext().getRequestDispatcher("/jsp/profile/articles/edit_article.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArticleCategory> categories = new ArrayList<>();
        var cats = request.getParameterValues("categories");
        if (cats!=null){
            for (var c: cats){
                ArticleCategory cat = new ArticleCategory();
                cat.setId(Integer.parseInt(c));
                categories.add(cat);
            }
        }
        Article article;
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");;
        int article_id = Integer.parseInt(pathParts[pathParts.length-1]);
        article = new ArticleService().getEntityById(article_id);
        article.setTitle(new String(request.getParameter("title").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        article.setFulltext(new String(request.getParameter("text").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        article.setCategories(categories);
        new ArticleService().update(article);
        response.sendRedirect("http://localhost:8081/my_articles/1");
    }
}