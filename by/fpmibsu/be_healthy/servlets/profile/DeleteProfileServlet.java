package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.entity.Article;
import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.services.ArticleService;
import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteProfileServlet", value = "/DeleteProfileServlet")
public class DeleteProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Profile profile = new Profile();
        profile.setId(Integer.parseInt(pathParts[pathParts.length-1]));
        new ProfileService().delete(profile);
        response.sendRedirect("http://localhost:8081/admin");
    }
}
