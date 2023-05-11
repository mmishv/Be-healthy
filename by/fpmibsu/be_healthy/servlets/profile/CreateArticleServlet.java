package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.entity.*;
import by.fpmibsu.be_healthy.services.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.valueOf;

@WebServlet(name = "CreateArticleServlet", value = "/CreateArticleServlet")
public class CreateArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", new ArticleCategoryService().getAllJSON());
        getServletContext().getRequestDispatcher("/jsp/profile/create_article.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int authorId;
        String title, text;
        List<ArticleCategory> categories = new ArrayList<>();
        title =  new String(request.getParameter("title").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        var cats = request.getParameterValues("categories");
        if (cats!=null){
            for (var c: cats){
                ArticleCategory cat = new ArticleCategory();
                cat.setId(Integer.parseInt(c));
                categories.add(cat);
            }
        }
        text = new String(request.getParameter("text").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        authorId = (int) request.getSession().getAttribute("id");
        Article article = new Article();
        article.setTitle(title);
        article.setFulltext(text);
        article.setAuthorId(authorId);
        article.setCategories(categories);
        article.setDateOfPublication(valueOf(LocalDate.now()));
        new ArticleService().create(article);
        response.sendRedirect("http://localhost:8081/my_articles/1");
    }
}
