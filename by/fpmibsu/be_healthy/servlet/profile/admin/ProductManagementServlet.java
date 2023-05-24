package by.fpmibsu.be_healthy.servlet.profile.admin;

import by.fpmibsu.be_healthy.entity.Product;
import by.fpmibsu.be_healthy.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ProductManagementServlet", value = "/products_management/*")
public class ProductManagementServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ProductManagementServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Transition to admin product management page");
        request.setAttribute("items", new ProductService().getAll());
        getServletContext().getRequestDispatcher("/jsp/profile/admin/admin-categories-products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Transition in order to edit product base");
        String pathInfo = request.getPathInfo();
        String[] path = pathInfo.split("/");
        String[] pathParts = path[path.length-1].split("-");
        String action = pathParts[0];
        if (action.equals("delete"))
            new ProductService().delete(Integer.parseInt(pathParts[1]));
        else{
            Product product = new Product();
            product.setName(new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            product.setCalories(Integer.parseInt(request.getParameter("c")));
            product.setProteins(Double.parseDouble(request.getParameter("b")));
            product.setFats(Double.parseDouble(request.getParameter("j")));
            product.setCarbohydrates(Double.parseDouble(request.getParameter("u")));
            if (action.equals("edit")){
                product.setId(Integer.parseInt(pathParts[1]));
                new ProductService().update(product);
            }
            else if(action.equals("create")){
                new ProductService().create(product);
            }
        }
        response.sendRedirect(request.getContextPath()+"/products_management");
    }
}
