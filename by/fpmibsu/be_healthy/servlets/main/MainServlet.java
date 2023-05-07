package by.fpmibsu.be_healthy.servlets.main;

import by.fpmibsu.be_healthy.services.ArticleService;
import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "MainServlet", value = "/MainServlet")
public class MainServlet extends HttpServlet {
    final int ARTICLES_PER_PAGE = 6;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sex = "";
        double weight = 0, height = 0, age = 0, activity = 0, goal = 0, result = 0;
        if (request.getParameter("age") != null && request.getParameter("weight") != null &&
                request.getParameter("height") != null) {
            sex = request.getParameter("sex");
            weight = Double.parseDouble(request.getParameter("weight"));
            height = Double.parseDouble(request.getParameter("height"));
            age = Double.parseDouble(request.getParameter("age"));
            activity = Double.parseDouble(request.getParameter("activity"));
            goal = Double.parseDouble(request.getParameter("goal"));
            result = (Objects.equals(sex, "female")) ? 447.6 + 9.2 * weight + 3.1 * height - 4.3 * age :
                    88.36 + 13.4 * weight + 4.8 * height - 5.7 * age;
            result *= activity * goal;
            request.setAttribute("result", result);
        }
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int page = Integer.parseInt(pathParts.length==0? String.valueOf(1) : pathParts[pathParts.length-1]);
        int article_cnt = 0;
        try {
            article_cnt = new ArticleService().getNumberOfArticles();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int page_cnt = (int) (1.0 * (article_cnt + ARTICLES_PER_PAGE - 1)/ARTICLES_PER_PAGE);
        request.setAttribute("page_cnt", page_cnt);
        request.setAttribute("cur_page", page);
        try {
            request.setAttribute("articles", new ArticleService().getPageJSON(page, ARTICLES_PER_PAGE));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/jsp/main/main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
