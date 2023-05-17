package by.fpmibsu.be_healthy.servlets.products;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MixerServlet", value = "/mixer/*")
public class MixerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MixerServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to mixer page");
        int cnt = 1;
        Recipe r = new Recipe();
        while (request.getParameter("quantity" + cnt) != null){
            while (request.getParameter("product" + cnt) != null) {
                Product p = new ProductService().getEntityById(Integer.parseInt(request.getParameter("product" + cnt)));
                Ingredient i = new Ingredient(p);
                i.setQuantity(Integer.parseInt(request.getParameter("quantity" + cnt)));
                i.setQuantity(Integer.parseInt(request.getParameter("quantity" + cnt)));
                r.addIngredient(i);
                cnt++;
            }
        }
        var kbju = r.getFullKBJU();
        request.setAttribute("k", kbju.get("k"));
        request.setAttribute("b", kbju.get("b"));
        request.setAttribute("j", kbju.get("j"));
        request.setAttribute("u", kbju.get("u"));
        request.setAttribute("weight", kbju.get("weight").toBigInteger());
        request.setAttribute("products", new ObjectMapper().writeValueAsString(r.getIngredients()));
        getServletContext().getRequestDispatcher("/jsp/product/mixer.jsp").forward(request, response);
    }
}
