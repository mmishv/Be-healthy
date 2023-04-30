package by.fpmibsu.be_healthy.servlets;
import by.fpmibsu.be_healthy.dao.ProfileDao;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        String password = (String) request.getAttribute("reg_password1"),
                login = (String) request.getAttribute("reg_login");
        if (Objects.equals(password, (String) request.getAttribute("reg_password2"))){
        session.setAttribute("password", password);
        session.setAttribute("login", login);
        session.setAttribute("isLogged", true);
            try {
                new ProfileDao().register(login, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendRedirect(super.getServletContext().getContextPath());
    }
}
