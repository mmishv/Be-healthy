package by.fpmibsu.be_healthy.servlet.profile.admin;

import by.fpmibsu.be_healthy.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MainAdminServlet", value = "/admin/*")
public class MainAdminServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MainAdminServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to admin main page");
        request.setAttribute("profiles", new ProfileService().getAll());
        request.setAttribute("my_id", request.getSession().getAttribute("id"));
        getServletContext().getRequestDispatcher("/jsp/profile/admin/admin-users.jsp").forward(request, response);
    }
}
