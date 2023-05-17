package by.fpmibsu.be_healthy.servlets.profile.articles;

import by.fpmibsu.be_healthy.entity.*;
import by.fpmibsu.be_healthy.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.valueOf;

@WebServlet(name = "CreateArticleServlet", value = "/create_article")
public class CreateArticleServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(CreateArticleServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to create article page");
        request.setAttribute("categories", new ArticleCategoryService().getAllJSON());
        getServletContext().getRequestDispatcher("/jsp/profile/articles/create_article.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition in order to add article");
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
        response.sendRedirect(request.getContextPath()+"/my_articles/1");
    }
}
