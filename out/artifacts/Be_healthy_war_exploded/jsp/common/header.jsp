<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2023
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static java.sql.Date.valueOf" %>
<%@ page import="java.time.LocalDate" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Be healthy</title>
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
<nav class="navbar navbar-expand-md sticky-top" style="background-color: #114630;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" style="padding:0px;">
                <img src="../../assets/logo.png" style="width:100px; height: auto">
            </a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav mx-auto" style="width: 100%; justify-content: space-around">

                <li class="nav-item">
                    <a class="nav-link active" href="/main/1">
                        Главная</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/diary/<%=valueOf(LocalDate.now().toString())%>">
                        Дневник</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/product">Продукты</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/recipes/1">Рецепты</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/forum">Форум</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Профиль</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
