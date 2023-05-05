package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        String password = request.getParameter("password"),
                login = request.getParameter("login");
        try {
            if (new ProfileService().isProfileExist(login, password)){
                session.setAttribute("password", password);
                session.setAttribute("login", login);
                session.setAttribute("id", new ProfileService().getIdByLogin(login));
                response.sendRedirect("/profile");
            }
            else{
                getServletContext().getRequestDispatcher("/jsp/profile/auth.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
