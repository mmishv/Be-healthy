package by.fpmibsu.be_healthy.servlet.profile;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@MultipartConfig
@WebServlet(name = "AboutMeServlet", value = "/profile")
public class AboutMeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AboutMeServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to main profile page");
        Profile  profile = new ProfileService().getEntityById((Integer.parseInt(request.getSession().getAttribute("id").toString())));
        request.setAttribute("profile", profile);
        getServletContext().getRequestDispatcher("/jsp/profile/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Transition in order to change profile information");
        Profile  profile = new ProfileService().getEntityById((Integer.parseInt(request.getSession().getAttribute("id").toString())));
        if (!Objects.equals(request.getParameter("avatar"), "")){
            Part filePart = request.getPart("avatar");
            InputStream fileContent = filePart.getInputStream();
            byte[] image = new byte[fileContent.available()];
            if (fileContent.available()!=0){
                fileContent.read(image);
                profile.setAvatar(image);
            }
        }
        if (!Objects.equals(request.getParameter("name"), ""))
            profile.setName(new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        new ProfileService().updateMainInfo(profile);
        response.sendRedirect(request.getContextPath()+"/profile");
    }
}
