package by.fpmibsu.be_healthy.servlets.products;

import by.fpmibsu.be_healthy.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductsServlet", value = "/product")
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", new ProductService().getAllJSON());
        getServletContext().getRequestDispatcher("/jsp/product/product.jsp").forward(request, response);
    }
}