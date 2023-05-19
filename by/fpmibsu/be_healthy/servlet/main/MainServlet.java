package by.fpmibsu.be_healthy.servlet.main;

import by.fpmibsu.be_healthy.service.ArticleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet({"/main/*", ""})
public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);
    final int ARTICLES_PER_PAGE = 6;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to main page");
        String sex = "";
        double weight = 0, height = 0, age = 0, activity = 0, goal = 0, result = 0;
        if (request.getParameter("age") != null && request.getParameter("weight") != null &&
                request.getParameter("height") != null) {
            logger.debug("Using calorie calculator on main page");
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
        String[] pathParts = pathInfo==null? new String[0]: pathInfo.split("/");
        int page = Integer.parseInt(pathParts.length==0? String.valueOf(1) : pathParts[pathParts.length-1]);
        int article_cnt = new ArticleService().getNumberOfArticles(true);
        int page_cnt = (int) (1.0 * (article_cnt + ARTICLES_PER_PAGE - 1)/ARTICLES_PER_PAGE);
        request.setAttribute("page_cnt", page_cnt);
        request.setAttribute("cur_page", page);
        request.setAttribute("articles", new ArticleService().getPageJSON(page, ARTICLES_PER_PAGE,true));
        getServletContext().getRequestDispatcher("/jsp/main/main.jsp").forward(request, response);
    }
}
