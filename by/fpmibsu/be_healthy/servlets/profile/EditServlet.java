package by.fpmibsu.be_healthy.servlets.profile;

import by.fpmibsu.be_healthy.entity.Profile;
import by.fpmibsu.be_healthy.services.ProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "EditServlet", value = "/EditServlet")
public class EditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Profile profile;
        try {
            profile = new ProfileService().getEntityById((Long.parseLong(request.getSession().getAttribute("id").toString())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!Objects.equals(request.getParameter("sex"), ""))
            profile.setSex(Objects.equals(request.getParameter("sex"), "1") ? "женский":"мужской");
        if (!Objects.equals(request.getParameter("height"), ""))
            profile.setHeight(Integer.parseInt(request.getParameter("height")));
        if (!Objects.equals(request.getParameter("age"), ""))
            profile.setAge(Integer.parseInt(request.getParameter("age")));
        if (!Objects.equals(request.getParameter("weight"), ""))
            profile.setWeight(Double.parseDouble(request.getParameter("weight")));
        if (!Objects.equals(request.getParameter("activity"), ""))
            profile.setActivity(Double.parseDouble(request.getParameter("activity")));
        if (!Objects.equals(request.getParameter("aim"), ""))
            profile.setGoal(Double.parseDouble(request.getParameter("aim")));
        if (!Objects.equals(request.getParameter("k"), ""))
            profile.getKBJU_norm().put("k", BigDecimal.valueOf(Double.parseDouble(request.getParameter("k"))));
        if (!Objects.equals(request.getParameter("b"), ""))
            profile.getKBJU_norm().put("b", BigDecimal.valueOf(Double.parseDouble(request.getParameter("b"))));
        if (!Objects.equals(request.getParameter("j"), ""))
            profile.getKBJU_norm().put("j", BigDecimal.valueOf(Double.parseDouble(request.getParameter("j"))));
        if (!Objects.equals(request.getParameter("u"), ""))
            profile.getKBJU_norm().put("u", BigDecimal.valueOf(Double.parseDouble(request.getParameter("u"))));
        try {
            new ProfileService().update(profile);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("http://localhost:8081/profile");
    }
}
