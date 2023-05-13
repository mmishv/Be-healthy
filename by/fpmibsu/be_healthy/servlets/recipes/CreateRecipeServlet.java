package by.fpmibsu.be_healthy.servlets.recipes;

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
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "createRecipeServlet", value = "/create_recipe")
@MultipartConfig
public class CreateRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", new ProductService().getAllJSON());
        request.setAttribute("categories", new RecipeCategoryService().getAllJSON());
        getServletContext().getRequestDispatcher("/jsp/recipes/new_recipe.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            ing.setId(Integer.parseInt(request.getParameter("ingredient" + cnt)));
            ing.setQuantity(Integer.parseInt(request.getParameter("quantity" + cnt)));
            ingredients.add(ing);
            cnt++;
        }
        authorId = (int) request.getSession().getAttribute("id");
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setCookingTime(cookingTime);
        recipe.setText(text);
        recipe.setImage(image);
        recipe.setAuthorId(authorId);
        recipe.setIngredients(ingredients);
        recipe.setCategories(categories);
        new RecipeService().create(recipe);
        response.sendRedirect(request.getContextPath()+"/my_recipes/1");
    }
}
