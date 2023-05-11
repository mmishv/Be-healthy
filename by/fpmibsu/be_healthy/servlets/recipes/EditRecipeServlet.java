package by.fpmibsu.be_healthy.servlets.recipes;

import by.fpmibsu.be_healthy.dao.RecipeDao;
import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Meal;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.services.ProductService;
import by.fpmibsu.be_healthy.services.RecipeCategoryService;
import by.fpmibsu.be_healthy.services.RecipeService;

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
import java.util.stream.Collectors;

import static java.sql.Date.valueOf;
@MultipartConfig
@WebServlet(name = "EditRecipeServlet", value = "/EditRecipeServlet")
public class EditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");;
        int recipe_id = Integer.parseInt(pathParts[pathParts.length-1]);
        Recipe recipe = new RecipeService().getEntityById(recipe_id);
        request.setAttribute("recipe", recipe);
        request.setAttribute("r_cats", recipe.getCategories().
                stream().map(RecipeCategory::getId).collect(Collectors.toList()));
        request.setAttribute("products", new ProductService().getAll());
        request.setAttribute("categories", new RecipeCategoryService().getAll());
        getServletContext().getRequestDispatcher("/jsp/recipes/edit_recipe.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cookingTime;
        byte[] image;
        List<Ingredient> ingredients = new ArrayList<>();
        List<RecipeCategory> categories = new ArrayList<>();
        cookingTime = Integer.parseInt(request.getParameter("cooking-time"));
        var cats = request.getParameterValues("categories");
        if (cats!=null){
            for (var c: cats){
                RecipeCategory cat = new RecipeCategory();
                cat.setId(Integer.parseInt(c));
                categories.add(cat);
            }
        }
        int cnt = 1;
        while (request.getParameter("ingredient" + cnt) != null) {
            Ingredient ing = new Ingredient();
            ing.setId(Integer.parseInt(request.getParameter("ingredient" + String.valueOf(cnt))));
            ing.setQuantity(Integer.parseInt(request.getParameter("quantity" + String.valueOf(cnt))));
            ingredients.add(ing);
            cnt++;
        }
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");;
        int recipe_id = Integer.parseInt(pathParts[pathParts.length-1]);
        Recipe recipe = new RecipeService().getEntityById(recipe_id);
        recipe.setTitle(new String(request.getParameter("title").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        recipe.setCookingTime(cookingTime);
        recipe.setText(new String(request.getParameter("text").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        if (request.getPart("image").getInputStream().available()!=0){
            image = new byte[request.getPart("image").getInputStream().available()];
            request.getPart("image").getInputStream().read(image);
            recipe.setImage(image);
        }
        recipe.setIngredients(ingredients);
        recipe.setCategories(categories);
        new RecipeService().update(recipe);
        response.sendRedirect("http://localhost:8081/my_recipes/1");
    }
}
