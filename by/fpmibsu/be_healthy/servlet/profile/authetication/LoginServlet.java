package by.fpmibsu.be_healthy.servlet.profile.authetication;

import by.fpmibsu.be_healthy.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition in order to log in");
        final HttpSession session = request.getSession();
        String password = request.getParameter("password"),
                login = request.getParameter("login");
        String stored_password = new ProfileService().getPasswordByLogin(login);
        if (BCrypt.checkpw(password, stored_password)) {
            session.setAttribute("login", login);
            session.setAttribute("id", new ProfileService().getIdByLogin(login));
            response.sendRedirect("/profile");
            logger.debug("Log in successfully");
        } else {
            logger.debug("Failed to log in");
            request.setAttribute("error_login", "Пароль неверный");
            getServletContext().getRequestDispatcher("/jsp/profile/auth.jsp").forward(request, response);
        }
    }
}
