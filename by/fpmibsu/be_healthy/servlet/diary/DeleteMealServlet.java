package by.fpmibsu.be_healthy.servlet.diary;

import by.fpmibsu.be_healthy.service.MealService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteMealServlet", value = "/delete-meal/*")
public class DeleteMealServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteMealServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition in order to remove meal");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String[] part =pathParts[pathParts.length-1].split("_");
        new MealService().delete(Integer.parseInt(part[0]));
        response.sendRedirect(request.getContextPath()+"/diary/"+part[1]);
    }
}
