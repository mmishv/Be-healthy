package by.fpmibsu.be_healthy.servlets.products;

import by.fpmibsu.be_healthy.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductsServlet", value = "/product")
public class ProductsServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ProductsServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to mixer and products base page");
        request.setAttribute("products", new ProductService().getAllJSON());
        getServletContext().getRequestDispatcher("/jsp/product/product.jsp").forward(request, response);
    }
}