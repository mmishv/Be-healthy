package by.fpmibsu.be_healthy.servlets.profile.authetication;
import by.fpmibsu.be_healthy.services.ProfileService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet({"/register"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        String password = BCrypt.hashpw(request.getParameter("reg_password1"),BCrypt.gensalt()),
                login = request.getParameter("reg_login");
        if (Objects.equals(request.getParameter("reg_password1"), request.getParameter("reg_password2")) &&
                new ProfileService().isLoginAvailable(login)){
            session.setAttribute("password", password);
            session.setAttribute("login", login);
            new ProfileService().register(login, password);
            response.sendRedirect("/profile");
        }
        else{
            getServletContext().getRequestDispatcher("/jsp/profile/auth.jsp").forward(request, response);
        }
        session.setAttribute("id", new ProfileService().getIdByLogin(login));
    }
}
