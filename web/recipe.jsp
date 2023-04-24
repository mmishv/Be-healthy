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
    <link rel="icon" href="assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/recipe.css">
    <link rel="stylesheet" href="css/air-datepicker.css">
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
    <div class="recipe-wrapper col-sm-10">
        <div class="card">
            <img src="./assets/recipes/recipe1.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Нисуаз</h5>
                <div class="description">Описание</div>
            </div>
            <div class="card-body">
                <!--          <a href="#" class="btn btn-primary">Посмотреть рецепт</a>-->
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
                    Посмотреть рецепт
                </button>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="exampleModalLongTitle">Нисуаз</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <img src="./assets/recipes/recipe1.jpg" class="modal-img">
                            <div class="general-info">
                                <div id="author">Автор:</div>
                                <div id="cooking-time">Время приготовления:</div>
                                <div id="recipe-category">Категории:</div>
                                <div id="ingredients">Ингредиенты:</div>
                            </div>
                            <h5 style="text-align: center; margin-top: 3%">Рецепт</h5>
                            <div id="recipe-text">
                                1. Нарезаем ломтиками 5–6 анчоусов, разминаем вилкой 250 г консервированного тунца.<br>
                                2. Варим вкрутую 2 перепелиных яйца и вместе с 2 помидорами режем дольками. <br>
                                3. Рубим пополам 100 г маслин без косточек. Или оставляем их целыми. <br>
                                4. Заправку смешиваем из 5 ст. л. оливкового масла, 1 ст. л. дижонской горчицы, 3
                                зубчиков чеснока, щепотки соли и перца.<br>
                                5. Выкладываем в тарелку салат, кладем кусочки рыбы, яиц и томатов, поливаем соусом,
                                украшаем маслинами.<br>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Закрыть</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="card">
            <img src="./assets/recipes/recipe3.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Цезарь</h5>
                <div class="description">Один из любимейших!</div>
            </div>
            <div class="card-body">
                <a href="#" class="btn btn-primary">Посмотреть рецепт</a>
            </div>
        </div>
        <div class="card">
            <img src="./assets/recipes/recipe2.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Кобб-салат</h5>
                <div class="description">Описание</div>
            </div>
            <div class="card-body">
                <a href="#" class="btn btn-primary">Посмотреть рецепт</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
