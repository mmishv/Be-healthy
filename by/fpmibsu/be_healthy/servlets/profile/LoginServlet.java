package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.services.ProfileService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        String password = request.getParameter("password"),
                login = request.getParameter("login");
        try {
            String stored_password = new ProfileService().getPasswordByLogin(login);
            if (BCrypt.checkpw(password, stored_password)){
                session.setAttribute("password", stored_password);
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
