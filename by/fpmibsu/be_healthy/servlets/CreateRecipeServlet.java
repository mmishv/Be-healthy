package by.fpmibsu.be_healthy.servlets;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.services.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        request.setCharacterEncoding("UTF-8");
        int authorId, cookingTime;
        String title, text;
        byte[] image;
        List<Ingredient> ingredients = new ArrayList<>();
        List<RecipeCategory> categories = new ArrayList<>();
            title = request.getParameter("title");
            cookingTime = Integer.parseInt(request.getParameter("cooking-time"));
            for (var c: request.getParameterValues("categories")){
                RecipeCategory cat = new RecipeCategory();
                cat.setId(Integer.parseInt(c));
                categories.add(cat);
            }
            text = request.getParameter("text");
            //image = Files.readAllBytes(new File(request.getParameter("image")).toPath());
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
            recipe.setImage(Files.readAllBytes(Paths.get("C:\\Users\\Masha\\Pictures\\calorie_calc\\салат.jpg")));
            //recipe.setImage(image);
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
