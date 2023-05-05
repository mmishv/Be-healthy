package by.fpmibsu.be_healthy.servlets.products;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.MealProduct;
import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MixerServlet", value = "/MixerServlet")
public class MixerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cnt = 1;
        if (request.getParameter("quantity" + cnt) != null){
            Recipe r = new Recipe();
            while (request.getParameter("product" + cnt) != null) {
                Product p;
                try {
                    p = new ProductService().getEntityById(Integer.parseInt(request.getParameter("product" + cnt)));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Ingredient i = new Ingredient(p);
                i.setQuantity(Integer.parseInt(request.getParameter("quantity" + cnt)));
                MealProduct product = new MealProduct();
                product.setId(Integer.parseInt(request.getParameter("product" + cnt)));
                product.setQuantity(Integer.parseInt(request.getParameter("quantity" + cnt)));
                r.addIngregient(i);
                cnt++;
            }
            var kbju = r.getKBJU();
            request.setAttribute("cal", kbju.get("k"));
            request.setAttribute("prot", kbju.get("b"));
            request.setAttribute("fats", kbju.get("j"));
            request.setAttribute("carb", kbju.get("u"));
        }
        try {
            request.setAttribute("products", new ProductService().getAllJSON());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/jsp/product/product.jsp").forward(request, response);
    }
}