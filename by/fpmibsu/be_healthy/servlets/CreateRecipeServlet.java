package by.fpmibsu.be_healthy.servlets;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.services.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static java.sql.Date.valueOf;

@WebServlet(name = "createRecipeServlet", value = "/createRecipeServlet")
@MultipartConfig
public class CreateRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("products", new ProductService().getAllJSON());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            request.setAttribute("categories", new RecipeCategoryService().getAllJSON());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/jsp/new_recipe.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        int authorId, cookingTime;
        String title, text;
        byte[] image;
        List<Ingredient> ingredients = new ArrayList<>();
        List<RecipeCategory> categories = new ArrayList<>();
            title =  new String(request.getParameter("title").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            cookingTime = Integer.parseInt(request.getParameter("cooking-time"));
            var cats = request.getParameterValues("categories");
            if (cats!=null){
                for (var c: cats){
                    RecipeCategory cat = new RecipeCategory();
                    cat.setId(Integer.parseInt(c));
                    categories.add(cat);
                }
            }
            text = new String(request.getParameter("text").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            Part filePart = request.getPart("image");
            InputStream fileContent = filePart.getInputStream();
            image = new byte[fileContent.available()];
            fileContent.read(image);
            int cnt = 1;
            while (request.getParameter("ingredient" + cnt) != null) {
                Ingredient ing = new Ingredient();
                ing.setId(Integer.parseInt(request.getParameter("ingredient" + String.valueOf(cnt))));
                ing.setQuantity(Integer.parseInt(request.getParameter("quantity" + String.valueOf(cnt))));
                ingredients.add(ing);
                cnt++;
            }
            try {
                authorId = new ProfileService().getIdByLogin((String) request.getSession().getAttribute("login"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Recipe recipe = new Recipe();
            recipe.setTitle(title);
            recipe.setCookingTime(cookingTime);
            recipe.setText(text);
            recipe.setImage(image);
            recipe.setAuthorId(authorId);
            recipe.setIngredients(ingredients);
            recipe.setCategories(categories);
            recipe.setDateOfPublication(valueOf(LocalDate.now()));
            try {
                new RecipeService().create(recipe);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        response.sendRedirect("http://localhost:8081/recipes");
    }
}
