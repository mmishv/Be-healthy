package by.fpmibsu.be_healthy.servlet.profile.authetication;
import by.fpmibsu.be_healthy.service.ProfileService;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet({"/register"})
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition in order to register");
        final HttpSession session = request.getSession();
        String password = BCrypt.hashpw(request.getParameter("reg_password1"),BCrypt.gensalt()),
                login = request.getParameter("reg_login");
        if (Objects.equals(request.getParameter("reg_password1"), request.getParameter("reg_password2")) &&
                new ProfileService().isLoginAvailable(login)){
            logger.debug("Registered successfully");
            session.setAttribute("password", password);
            session.setAttribute("login", login);
            new ProfileService().register(login, password);
            session.setAttribute("id", new ProfileService().getIdByLogin(login));
            response.sendRedirect("/profile");
        }
        else{
            logger.debug("Failed to register");
            getServletContext().getRequestDispatcher("/jsp/profile/auth.jsp").forward(request, response);
        }
    }
}
