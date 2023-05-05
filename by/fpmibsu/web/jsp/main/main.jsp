<%@ page import="java.util.Objects" %>
<%--
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
    <title>Главная</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/style.css">
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
<div style="display: flex">
    <div class="calculator">
        <h2>Калькулятор калорий</h2>
        <form action="jsp/main/main.jsp" method="GET">
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
                    <input type="number" class="form-control" id="height" placeholder="Рост, см" name="height" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="weight" class="col-sm-3 col-form-label">Вес:</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" id="weight" placeholder="Вес" name="weight" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="age" class="col-sm-3 col-form-label">Возраст:</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" id="age" placeholder="Возраст" name="age" required>
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
                    <button type="reset" class="btn btn-primary"
                            style="background-color: #00000000; border: solid 2px white;">Сбросить
                    </button>
                </div>
            </div>
            <%
                String sex = "";
                double weight = 0, height = 0, age = 0, activity = 0, goal = 0, result = 0;
                if (request.getParameter("age") != null && request.getParameter("weight") != null &&
                        request.getParameter("height") != null) {
                    sex = request.getParameter("sex");
                    weight = Double.parseDouble(request.getParameter("weight"));
                    height = Double.parseDouble(request.getParameter("height"));
                    age = Double.parseDouble(request.getParameter("age"));
                    activity = Double.parseDouble(request.getParameter("activity"));
                    goal = Double.parseDouble(request.getParameter("goal"));
                    result = (Objects.equals(sex, "female")) ? 447.6 + 9.2 * weight + 3.1 * height - 4.3 * age :
                            88.36 + 13.4 * weight + 4.8 * height - 5.7 * age;
                    result *= activity * goal;
            %>
            <tr>
                <td> Результат: <%=(int) result%>
                </td>
            </tr>
            <%
                }
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