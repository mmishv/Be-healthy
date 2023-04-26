<%@ page import="by.fpmibsu.be_healthy.entity.Recipe" %>
<%@ page import="java.util.ArrayList" %><%--
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
            <li class="list-group-item">Выпечка</li>
            <li class="list-group-item">Гарниры</li>
            <li class="list-group-item">Первые блюда</li>
            <li class="list-group-item">Вторые блюда</li>
            <li class="list-group-item">Закуски</li>
            <li class="list-group-item">Салаты</li>
            <li class="list-group-item">Завтраки</li>
            <li class="list-group-item">Десерты</li>
            <li class="list-group-item">Напитки</li>
            <li class="list-group-item">Мясо</li>
            <li class="list-group-item">Морепродукты</li>
            <li class="list-group-item">Для микроволновки</li>
            <li class="list-group-item">Для мультиварки</li>
        </ul>
    </div>
    <div class="col-sm-10">
        <button type="button" class="btn add-button" onclick="createRecipe()">Добавить рецепт</button>
        <div class="recipe-wrapper">
<%               ArrayList<Recipe> recipes = (ArrayList<Recipe>) request.getAttribute("recipes");
                for (Recipe recipe : recipes){
%>
            <div class="card">
                <img src="data:image/jpeg;base64,<%=recipe.getBase64image()%>" class="modal-img">
                <div class="card-body">
                    <h5 class="card-title"><%=recipe.getTitle()%></h5>
                </div>
                <div class="card-body">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong<%=recipe.getId()%>"
                    onclick="showRecipe(<%=recipe.getId()%>)">
                        Посмотреть рецепт
                    </button>
                </div>

                <div class="modal fade" id="exampleModalLong<%=recipe.getId()%>" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLongTitle<%=recipe.getId()%>" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="exampleModalLongTitle<%=recipe.getId()%>"><%=recipe.getTitle()%></h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <img src="data:image/jpeg;base64,<%=recipe.getBase64image()%>" class="modal-img">
                                <div class="general-info">
                                    <div id="author<%=recipe.getId()%>">Автор: <%=recipe.getAuthorId()%></div>
                                    <div id="cooking-time<%=recipe.getId()%>">Время приготовления: <%=recipe.getCookingTime()%></div>
                                    <div id="recipe-category<%=recipe.getId()%>">Категории:</div>
                                    <div id="ingredients<%=recipe.getId()%>">Ингредиенты:</div>
                                </div>
                                <h5 style="text-align: center; margin-top: 3%">Рецепт</h5>
                                <div id="recipe-text<%=recipe.getId()%>">
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
<script>
    function createRecipe() {
        document.location.href = "jsp/new_recipe.jsp";
    }
    function showRecipe(id) {
        document.getElementById(id).classList.toggle("show");
    }
</script>
</body>
</html>
