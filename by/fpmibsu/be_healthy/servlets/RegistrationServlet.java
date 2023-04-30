package by.fpmibsu.be_healthy.servlets;
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
            session.setAttribute("isLogged", true);
                try {
                    new ProfileService().register(login, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(request, response);
            }
            else{
                getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
