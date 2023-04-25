<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2023
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Главная</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/new_recipe.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
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
<div class="wrapper col-sm-9" style="margin: auto;">
    <div class="form-group row">
        <label for="recipe-title" class="col-sm-4 col-form-label">Название: </label>
        <div class="col-sm-8">
            <input class="form-control" id="recipe-title" placeholder="Например, оладьи" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="recipe-cooking-time" class="col-sm-4 col-form-label">Время приготовления, мин: </label>
        <div class="col-sm-8">
            <input type="number" class="form-control" id="recipe-cooking-time" placeholder="Например, 30" required>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-4 bold">Категории:</div>
        <div class="col-sm-8 row" style="margin: 0;">
            <div class="form-check col-sm-6">
                <input class="form-check-input" type="checkbox" value="" id="category1">
                <label class="form-check-label" for="category1">
                    Default checkbox
                </label>
            </div>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-4 bold">Ингредиенты:</div>
        <div class="col-sm-8" id="ingredients-list">
            <div class="row ingredient-option">
                <select id="ingredient1" class="form-control col-sm-7"></select>
                <input type="number" class="form-control col-sm-2" placeholder="кол-во" required>
                <button class="col-sm-1 ing-button" onclick="addIngredient(this)">+</button>
            </div>
        </div>
    </div>
    <div class="form-group row">
        <label for="recipe-photo" class="col-sm-4 bold">Фото рецепта:</label>
        <div class="col-sm-8">
            <input type="file" class="form-control-file" id="recipe-photo">
        </div>
    </div>
    <div class="form-group row">
        <label for="recipe-description" class="col-sm-4 bold">Описание рецепта:</label>
        <textarea class="form-control col-sm-8" id="recipe-description" rows="5"></textarea>
    </div>
    <div class="form-group row">
        <button type="submit" class="btn btn-primary"
                style="background-color:#114630 !important; border: #114630 !important;">
            Добавить
        </button>
        <button type="reset" class="btn btn-primary" onclick="goToRecipes()"
                style="background-color: #00000000; border: solid 2px #114630; color: #114630">
            Вернуться к рецептам
        </button>
    </div>

</div>
<script>
    function addIngredient(e) {
        if (e.classList.contains('disabled')) {
            e.parentElement.remove();
        } else {
            e.classList.add('disabled');
            e.style.backgroundColor = '#114630a8';
            e.innerHTML = '-';
            let selectField = document.createElement('select');
            selectField.className = "form-control col-sm-7";
            let inputField = document.createElement('input');
            inputField.className = "form-control col-sm-2";
            inputField.type = "number";
            inputField.placeholder = "кол-во";
            let button = document.createElement('button');
            button.className = "col-sm-1 ing-button";
            button.innerHTML = '+';
            button.addEventListener('click', () => addIngredient(button));
            let container = document.createElement('div');
            container.classList.add('row');
            container.appendChild(selectField);
            container.appendChild(inputField);
            container.appendChild(button);
            container.classList.add('ingredient-option');
            document.getElementById('ingredients-list').appendChild(container);
        }
    }

    function goToRecipes() {
        document.location.href = "recipe.jsp";
    }
</script>
</body>
</html>
