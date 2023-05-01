<%@ page import="by.fpmibsu.be_healthy.entity.Recipe" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="by.fpmibsu.be_healthy.services.ProfileService" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="by.fpmibsu.be_healthy.entity.RecipeCategory" %>
<%@ page import="by.fpmibsu.be_healthy.entity.Ingredient" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2023
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Рецепты</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/recipe.css">
    <link rel="stylesheet" href="../css/air-datepicker.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:directive.include file="header.jsp"></jsp:directive.include>
<div class="background">
    <div style="opacity: 0.8; background: black; width: 100%; height: 100%;"></div>
</div>
<div class="page-recipe">
    <div class="categories col-sm-2">
        <ul class="list-group">
            <%
                ArrayList<RecipeCategory> categories =
                        new ObjectMapper().readValue(request.getAttribute("categories").toString(),
                                new TypeReference<ArrayList<RecipeCategory>>() {});
                String cat_name;
                for (RecipeCategory cat : categories) {
                    cat_name = cat.getName();
            %>
            <li class="list-group-item"><%=cat_name%>
            </li>
            <%
                }
            %>
        </ul>
    </div>
    <div class="col-sm-10">
        <button type="button" class="btn add-button">
            <a href="create_recipe">Добавить рецепт</a></button>
        <div class="recipe-wrapper">
            <% ArrayList<Recipe> recipes = new ObjectMapper().readValue(request.getAttribute("recipes").toString(),
                    new TypeReference<ArrayList<Recipe>>() {});
                String name;
                for (Recipe recipe : recipes) {
                    HashMap<String, BigDecimal> kbju = recipe.getKBJU();
                    try {
                        name = new ProfileService().getEntityById(recipe.getId()).getLogin();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
            %>
            <div class="card">
                <img src="data:image/jpeg;base64,<%=recipe.getBase64image()%>" class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title"><%=recipe.getTitle()%>
                    </h5>
                    <h6 class="card-title"><%=name%>
                    </h6>
                    <h6 class="card-title"><%=recipe.getCookingTime()%> минут</h6>
                </div>
                <div class="card-body">
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                            data-target="#<%=recipe.getId()%>">
                        Посмотреть рецепт
                    </button>
                </div>

                <div class="modal fade" id="<%=recipe.getId()%>" tabindex="-1" role="dialog"
                     aria-labelledby="<%=recipe.getId()%>" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="<%=recipe.getId()%>"><%=recipe.getTitle()%>
                                </h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <img src="data:image/jpeg;base64,<%=recipe.getBase64image()%>" class="modal-img">
                                <div class="general-info">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col" class="col-md-3">К</th>
                                            <th scope="col" class="col-md-3">Б</th>
                                            <th scope="col" class="col-md-3">Ж</th>
                                            <th scope="col" class="col-md-3">У</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td><%=kbju.get("k")%></td>
                                            <td><%=kbju.get("b")%></td>
                                            <td><%=kbju.get("j")%></td>
                                            <td><%=kbju.get("u")%></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="author<%=recipe.getId()%>">Автор: <%=name%>
                                    </div>
                                    <div class="cooking-time<%=recipe.getId()%>">Время
                                        приготовления: <%=recipe.getCookingTime()%>  минут
                                    </div>
                                    <div class="recipe-category<%=recipe.getId()%> " style="display: inline">
                                        Категории:
                                    </div>
                                    <%
                                        String category;
                                        int n = recipe.getCategories().size();
                                        for (RecipeCategory cat : recipe.getCategories()) {
                                            category = cat.getName().toLowerCase();
                                            if (--n != 0) {
                                    %>
                                    <div class="category" style="display: inline"><%= category %>,</div>
                                    <%
                                    } else {
                                    %>
                                    <div class="category" style="display: inline"><%= category %>
                                    </div>
                                    <%
                                            }
                                        }
                                    %>
                                </div>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col"></th>
                                        <th scope="col" class="col-md-7">Ингредиент</th>
                                        <th scope="col" class="col-md-2">Количество</th>
                                        <th scope="col" class="col-md-3">Мера измерения</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        int cnt=1;
                                        for (Ingredient i : recipe.getIngredients()) {
                                    %>
                                    <tr>
                                        <th scope="row"><%=cnt++%></th>
                                        <td><%=i.getName()%></td>
                                        <td style="text-align: center;"><%=i.getQuantity()%></td>
                                        <td style="text-align: center;"><%=i.getUnit()%></td>
                                    </tr>
                                    </tbody>
                                    <%
                                        }
                                    %>
                                </table>
                                <h5 style="text-align: center; margin-top: 3%">Рецепт</h5>
                                <div id="recipe-text<%=recipe.getId()%>" class="recipe-text">
                                    <%=recipe.getText()%><br>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Закрыть</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
