package by.fpmibsu.be_healthy.servlets.recipes;

import by.fpmibsu.be_healthy.entity.Ingredient;
import by.fpmibsu.be_healthy.entity.Recipe;
import by.fpmibsu.be_healthy.entity.RecipeCategory;
import by.fpmibsu.be_healthy.services.ProductService;
import by.fpmibsu.be_healthy.services.RecipeCategoryService;
import by.fpmibsu.be_healthy.services.RecipeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
@WebServlet(name = "EditRecipeServlet", value = "/edit_recipe/*")
public class EditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");;
        int recipe_id = Integer.parseInt(pathParts[pathParts.length-1].split("_")[0]);
        Recipe recipe = new RecipeService().getEntityById(recipe_id);
        request.setAttribute("recipe", recipe);
        request.setAttribute("r_cats", recipe.getCategories().
                stream().map(RecipeCategory::getId).collect(Collectors.toList()));
        request.setAttribute("products", new ProductService().getAll());
        request.setAttribute("categories", new RecipeCategoryService().getAll());
        request.setAttribute("is_admin",pathParts[pathParts.length-1].split("_").length==1? "":"_admin" );
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
            ing.setId(Integer.parseInt(request.getParameter("ingredient" + cnt)));
            ing.setQuantity(Integer.parseInt(request.getParameter("quantity" + cnt)));
            ingredients.add(ing);
            cnt++;
        }
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int recipe_id = Integer.parseInt(pathParts[pathParts.length-1].split("_")[0]);
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
        if (pathParts[pathParts.length-1].split("_").length==1)
            response.sendRedirect(request.getContextPath()+"/my_recipes/1");
        else
            response.sendRedirect(request.getContextPath()+"/recipes_management");
    }
}
