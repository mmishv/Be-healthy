<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.05.2023
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Главная</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/profile.css">
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
<jsp:directive.include file="../common/header.jsp"></jsp:directive.include>
<div class="background">
    <div style="opacity: 0.8; background: black; width: 100%; height: 100%;"></div>
</div>
<div class="wrapper row">
    <div class="sections col-sm-2">
        <ul class="list-group">
            <li class="list-group-item">
                <a class="nav-link" href="/profile">Обо мне</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="/diary/<%=valueOf(LocalDate.now().toString())%>">
                    Приёмы пищи</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="my_recipes/1">Мои рецепты</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="">Мои статьи</a></li>
        </ul>
    </div>
    <div class="col-sm-10">
        <div class="inf">
            <form>
                <fieldset class="form-group">
                    <div class="row">
                        <legend class="col-form-label col-sm-3 pt-0">Пол:</legend>
                        <div class="col-sm-9">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gridRadios" id="gender"
                                       value="option1" checked>
                                <label class="form-check-label" for="gender">
                                    Женский
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gridRadios" id="gender2"
                                       value="option2">
                                <label class="form-check-label" for="gender2">
                                    Мужской
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="form-group row">
                    <label for="height" class="col-sm-4 col-form-label">Рост:</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="height" placeholder="Рост, см">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="weight" class="col-sm-4 col-form-label">Вес:</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="weight" placeholder="Вес">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="age" class="col-sm-4 col-form-label">Возраст:</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="age" placeholder="Возраст">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-4 col-form-label">Замеры:</label>
                    <div class="param-wrapper col-sm-8">
                        <input class="form-control param" id="param1">
                        <input class="form-control param" id="param2">
                        <input class="form-control param" id="param3">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="aim" class="col-sm-4 col-form-label">Цель</label>
                    <select id="aim" class="form-control col-sm-7">
                        <option selected>Поддержание веса</option>
                        <option>Набор веса</option>
                        <option>Сброс веса</option>
                    </select>
                </div>

                <div class="form-group row">
                    <div class="col-sm-3">
                        <button class="btn btn-primary btn-black">Сохранить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
