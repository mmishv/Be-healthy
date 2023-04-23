<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: Masha
  Date: 23.04.2023
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Be healthy</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="assets/favicon2.png" type="image/x-icon">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <nav class="navbar navbar-expand-md sticky-top" style="background-color: #114630;">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#" style="padding:0px;">
          <img src="assets/logo.png" style="width:100px; height: auto">
        </a>
      </div>

      <div class="collapse navbar-collapse" >
        <ul class="nav navbar-nav mx-auto" style="width: 100%; justify-content: space-around">

          <li class="nav-item">
            <a class="nav-link active" href="#">
              <!--                        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">-->
              <!--                        <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5 5 5Z"/>-->
              <!--                    </svg>-->
              Главная</a>
          </li>
          <li class="nav-item" >
            <a class="nav-link" href="./diary.html">
              <!--                        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-journal-text" viewBox="0 0 16 16">-->
              <!--                        <path d="M5 10.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>-->
              <!--                        <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z"/>-->
              <!--                        <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z"/>-->
              <!--                    </svg>-->
              Дневник</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./product.html">Продукты</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./recipe.html">Рецепты</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Форум</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Профиль</a>
            <form class="dropdown-menu dropdown-menu-right p-4" aria-labelledby="dropdownMenuButton" id="profile-form">
              <h4>Вход</h4>
              <div class="form-group row">
                <label for="DropdownFormLogin1"  class="col-sm-4">Логин</label>
                <input type="text" class="form-control col-sm-8" id="DropdownFormLogin1">
              </div>
              <div class="form-group row">
                <label for="DropdownFormPassword1"  class="col-sm-4">Пароль</label>
                <input type="password" class="form-control col-sm-8" id="DropdownFormPassword1">
              </div>
              <div style="display: flex; justify-content: flex-end;">
                <button type="submit" class="btn btn-primary btn-black" >Войти</button>
              </div>
              <h4>Регистрация</h4>
              <div class="form-group row">
                <label for="DropdownFormLogin2"  class="col-sm-4">Логин</label>
                <input type="text" class="form-control col-sm-8" id="DropdownFormLogin2">
              </div>
              <div class="form-group row">
                <label for="DropdownFormPassword2"  class="col-sm-4">Пароль</label>
                <input type="password" class="form-control col-sm-8" id="DropdownFormPassword2">
              </div>
              <div class="form-group row">
                <label for="DropdownFormPassword2-repeat"  class="col-sm-4">Подтвердите пароль</label>
                <input type="password" class="form-control col-sm-8" id="DropdownFormPassword2-repeat">
              </div>
              <div style="display: flex; justify-content: flex-end;">
                <button type="submit" class="btn btn-primary btn-black" >Зарегистрироваться</button>
              </div>
            </form>
          </li>

        </ul>
      </div>
    </div>
  </nav>
</head>
<body>
<div class="background">
  <div style="opacity: 0.8; background: black; width: 100%; height: 100%;"></div>
</div>
<div style="display: flex">
  <div class="calculator">
    <h2>Калькулятор калорий</h2>
    <form action = "main.jsp" method = "GET">
      <fieldset class="form-group">
        <div class="row">
          <legend class="col-form-label col-sm-3 pt-0">Пол:</legend>
          <div class="col-sm-9">
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="sex" value="female" id="gender" checked>
              <label class="form-check-label" for="gender">
                Женский
              </label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="sex" id="gender2" value="male">
              <label class="form-check-label" for="gender2">
                Мужской
              </label>
            </div>
          </div>
        </div>
      </fieldset>
      <div class="form-group row">
        <label for="height" class="col-sm-3 col-form-label">Рост:</label>
        <div class="col-sm-9">
          <input class="form-control" id="height" placeholder="Рост, см" name="height">
        </div>
      </div>
      <div class="form-group row">
        <label for="weight" class="col-sm-3 col-form-label">Вес:</label>
        <div class="col-sm-9">
          <input class="form-control" id="weight" placeholder="Вес" name="weight">
        </div>
      </div>
      <div class="form-group row">
        <label for="age" class="col-sm-3 col-form-label">Возраст:</label>
        <div class="col-sm-9">
          <input class="form-control" id="age" placeholder="Возраст" name="age">
        </div>
      </div>
      <div class="form-group row">
        <label for="activity" class="col-sm-3 col-form-label">Физическая активность</label>
        <select id="activity" class="form-control col-sm-8" name="activity">
          <option value="1" selected>Без учета физ. нагрузки</option>
          <option value="1.2">Сидячий образ жизни</option>
          <option value="1.375">Легкая активность(1-2 раза в неделю)</option>
          <option value="1.55">Умеренная активность (3-5 раз в неделю)</option>
          <option value="1.725">Высокая активность (более 5 раз в неделю)</option>
          <option value="1.9">Очень высокая активность (профессиональный спорт)</option>
        </select>
      </div>
      <div class="form-group row">
        <label for="aim" class="col-sm-3 col-form-label">Цель</label>
        <select id="aim" class="form-control col-sm-8" name="goal">
          <option value="1" selected>Поддержание веса</option>
          <option value="1.2">Набор веса</option>
          <option value="0.8">Снижение веса</option>
        </select>
      </div>

      <div class="form-group row">
        <div class="col-sm-3">
          <button type="submit" class="btn btn-primary btn-black">Рассчитать</button>
        </div>
        <div class="col-sm-3">
          <button type="reset" class="btn btn-primary" style="background-color: #00000000; border: solid 2px white;">Сбросить</button>
        </div>
      </div>
      <%
        boolean is_valid = true;
        String sex="";
        double weight = 0, height=0, age=0, activity=0, goal=0, result =0;
        if (request.getParameter("age") != null && request.getParameter("weight") != null &&
                request.getParameter("height") != null){
          sex = request.getParameter("sex");
          weight = Double.parseDouble(request.getParameter("weight"));
          height = Double.parseDouble(request.getParameter("height"));
          age = Double.parseDouble(request.getParameter("age"));
          activity = Double.parseDouble(request.getParameter("activity"));
          goal = Double.parseDouble(request.getParameter("goal"));
          result = (Objects.equals(sex, "female"))? 447.6 + 9.2 * weight + 3.1 * height - 4.3 * age :
                  88.36 + 13.4 * weight + 4.8 * height - 5.7 * age;
          result *= activity*goal;
          result = (int)result;
      %>
      <tr>
        <td> Результат: <%=result%></td>
      </tr>
      <%}
      %>
    </form>
  </div>
  <div class="articles-wrapper col-sm-5">
    <h3>Вам может быть интересно</h3>
    <div class="article">

    </div>
    <div class="article">

    </div>
    <div class="article">

    </div>
  </div>
</div>
</body>
</html>
