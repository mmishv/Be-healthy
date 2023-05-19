package by.fpmibsu.be_healthy.servlet.profile;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.entity.Role;
import by.fpmibsu.be_healthy.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddAdminServlet", value = "/change_role/*")
public class ChangeRoleServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ChangeRoleServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition in order to change user role");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Profile profile = new ProfileService().getEntityById(Integer.parseInt(pathParts[pathParts.length-1]));
        Role role = profile.getRole();
        role.setId(role.getId()==1? 2: 1);
        profile.setRole(role);
        new ProfileService().updateRole(profile);
        response.sendRedirect(request.getContextPath()+"/admin");
    }
}
