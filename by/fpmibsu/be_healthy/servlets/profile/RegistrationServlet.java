package by.fpmibsu.be_healthy.servlets.profile;
import by.fpmibsu.be_healthy.dao.ProfileDao;
import by.fpmibsu.be_healthy.services.ProfileService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        String password = request.getParameter("reg_password1"),
                login = request.getParameter("reg_login");
        try {
            if (Objects.equals(password, request.getParameter("reg_password2")) &&
                    new ProfileService().isLoginAvailable(login)){
            session.setAttribute("password", password);
            session.setAttribute("login", login);
                try {
                    new ProfileService().register(login, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect("/profile");
            }
            else{
                getServletContext().getRequestDispatcher("/jsp/profile/auth.jsp").forward(request, response);
            }
            session.setAttribute("id", new ProfileService().getIdByLogin(login));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
