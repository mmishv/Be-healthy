package by.fpmibsu.be_healthy.servlets.profile.admin;

import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MainAdminServlet", value = "/admin/*")
public class MainAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("profiles", new ProfileService().getAll());
        request.setAttribute("my_id", request.getSession().getAttribute("id"));
        getServletContext().getRequestDispatcher("/jsp/profile/admin/admin-users.jsp").forward(request, response);
    }
}
