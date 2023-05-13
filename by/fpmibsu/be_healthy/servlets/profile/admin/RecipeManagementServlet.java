package by.fpmibsu.be_healthy.servlets.profile.admin;

import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecipeManagementServlet", value = "/recipes_management")
public class RecipeManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("items", new RecipeService().getAll());
        getServletContext().getRequestDispatcher("/jsp/profile/admin/admin-recipes-articles.jsp").forward(request, response);
    }
}