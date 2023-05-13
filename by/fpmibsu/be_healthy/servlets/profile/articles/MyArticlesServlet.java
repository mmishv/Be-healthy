package by.fpmibsu.be_healthy.servlets.profile.articles;

import by.fpmibsu.be_healthy.services.ArticleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MyArticlesServlet", value = "/my_articles/*")
public class MyArticlesServlet extends HttpServlet {
    final int ARTICLES_PER_PAGE = 2;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int page = Integer.parseInt(pathParts[pathParts.length-1]);
        int article_cnt =  new ArticleService().getNumberOfArticlesWrittenBy((int) request.getSession().getAttribute("id"));
        int page_cnt = (int) (1.0 * (article_cnt + ARTICLES_PER_PAGE - 1)/ARTICLES_PER_PAGE);
        request.setAttribute("page_cnt", page_cnt);
        request.setAttribute("cur_page", page);
        request.setAttribute("articles", new ArticleService().getAuthorPageJSON(page, ARTICLES_PER_PAGE,
                (int) request.getSession().getAttribute("id")));
        getServletContext().getRequestDispatcher("/jsp/profile/articles/my_articles.jsp").forward(request, response);
    }
}
