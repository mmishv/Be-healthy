package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteProfileServlet", value = "/delete_profile/*")
public class DeleteProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        new ProfileService().delete(Integer.parseInt(pathParts[pathParts.length-1]));
        response.sendRedirect(request.getContextPath()+"/admin");
    }
}
