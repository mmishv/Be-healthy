package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.entity.Role;
import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddAdminServlet", value = "/change_role/*")
public class ChangeRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Profile profile = new ProfileService().getEntityById(Integer.parseInt(pathParts[pathParts.length-1]));
        Role role = profile.getRole();
        role.setId(role.getId()==1? 2: 1);
        profile.setRole(role);
        new ProfileService().updateRole(profile);
        response.sendRedirect("http://localhost:8081/admin");
    }
}
