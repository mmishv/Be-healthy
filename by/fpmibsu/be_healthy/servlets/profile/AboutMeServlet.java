package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
@MultipartConfig
@WebServlet(name = "AboutMeServlet", value = "/AboutMeServlet")
public class AboutMeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Profile profile;
        try {
            profile = new ProfileService().getEntityById((Long.parseLong(request.getSession().getAttribute("id").toString())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("profile", profile);
        getServletContext().getRequestDispatcher("/jsp/profile/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Profile profile;
        try {
            profile = new ProfileService().getEntityById((Long.parseLong(request.getSession().getAttribute("id").toString())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (request.getPart("avatar")!=null){
            Part filePart = request.getPart("avatar");
            InputStream fileContent = filePart.getInputStream();
            byte[] image = new byte[fileContent.available()];
            fileContent.read(image);
            if (image!=null)
                 profile.setAvatar(image);
        }
        if (request.getParameter("name")!=null)
            profile.setName(new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        try {
            new ProfileService().update(profile);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("http://localhost:8081/profile");
    }
}
