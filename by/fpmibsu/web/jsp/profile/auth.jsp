<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 29.04.2023
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Вход/Регистрация</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/air-datepicker.css">
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
    <style>
        .form-wrapper {
            width: 650px;
            margin: 3% auto 0 auto;
            position: relative;
            padding: 2% 3%;
            background-color: #114630;
            color: white;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<jsp:directive.include file="../common/header.jsp"></jsp:directive.include>
<div class="background">
    <div class="background-cover"></div>
</div>
<div>
    <div class="form-wrapper">
        <form method="POST" action="/login">
            <fieldset class="form-group">
                <h4>Вход</h4>
                <div class="form-group row">
                    <label for="DropdownFormLogin1" class="col-sm-4">Логин</label>
                    <input type="text" class="form-control col-sm-8" name="login" id="DropdownFormLogin1" required>
                </div>
                <div class="form-group row">
                    <label for="DropdownFormPassword1" class="col-sm-4">Пароль</label>
                    <input type="password" class="form-control col-sm-8" name="password" id="DropdownFormPassword1"
                           required>
                </div>
            </fieldset>
            <div style="display: flex; justify-content: flex-end;">
                <span class="error-message">${error_login}</span>
                <button type="submit" class="btn btn-primary btn-black scaled">Войти</button>
            </div>
        </form>
        <form method="POST" action="/register">
            <fieldset class="form-group">
                <h4>Регистрация</h4>
                <div class="form-group row">
                    <label for="DropdownFormLogin2" class="col-sm-4">Логин</label>
                    <input type="text" class="form-control col-sm-8" name="reg_login" id="DropdownFormLogin2" required>
                </div>
                <div class="form-group row">
                    <label for="DropdownFormPassword2" class="col-sm-4">Пароль</label>
                    <input type="password" class="form-control col-sm-8" name="reg_password1" id="DropdownFormPassword2"
                           required>
                </div>
                <div class="form-group row">
                    <label for="DropdownFormPassword2-repeat" class="col-sm-4">Подтвердите пароль</label>
                    <input type="password" class="form-control col-sm-8" name="reg_password2"
                           id="DropdownFormPassword2-repeat" required>
                </div>
                <div style="display: flex; justify-content: flex-end;">
                    <span class="error-message">${error_reg}</span>
                    <button type="submit" class="btn btn-primary btn-black scaled">Зарегистрироваться</button>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
