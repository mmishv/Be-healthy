package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@MultipartConfig
@WebServlet(name = "AboutMeServlet", value = "/profile")
public class AboutMeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Profile  profile = new ProfileService().getEntityById((Long.parseLong(request.getSession().getAttribute("id").toString())));
        request.setAttribute("profile", profile);
        getServletContext().getRequestDispatcher("/jsp/profile/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Profile  profile = new ProfileService().getEntityById((Long.parseLong(request.getSession().getAttribute("id").toString())));
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
