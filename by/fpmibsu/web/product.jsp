<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2023
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Продукты</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/product.css">
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
<div class="mixer col-sm-5">
    <h3 style="text-align: center; margin-bottom: 5%;">Микшер рецептов</h3>
    <div class="product-inf d-flex center">
        <div class="col-sm-7">
            <input class="form-control product" placeholder="Продукт">
        </div>
        <div class="col-sm-2">
            <input class="form-control quantity" placeholder="Кол-во">
        </div>
        <select class="form-control col-sm-2 measure">
            <option selected>шт.</option>
            <option>кг</option>
            <option>г</option>
            <option>л</option>
        </select>
    </div>
    <div class="product-inf d-flex center">
        <div class="col-sm-7">
            <input class="form-control product" placeholder="Продукт">
        </div>
        <div class="col-sm-2">
            <input class="form-control quantity" placeholder="Кол-во">
        </div>
        <select class="form-control col-sm-2 measure">
            <option selected>шт.</option>
            <option>кг</option>
            <option>г</option>
            <option>л</option>
        </select>
    </div>
    <div class="product-inf d-flex center">
        <div class="col-sm-7">
            <input class="form-control product" placeholder="Продукт">
        </div>
        <div class="col-sm-2">
            <input class="form-control quantity" placeholder="Кол-во">
        </div>
        <select class="form-control col-sm-2 measure">
            <option selected>шт.</option>
            <option>кг</option>
            <option>г</option>
            <option>л</option>
        </select>
    </div>
    <div class="add-button btn btn-primary">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" color="#114630" fill="currentColor"
             class="bi bi-plus" viewBox="0 0 16 16">
            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
        </svg>
    </div>
    <div style="display: flex; justify-content: flex-end;">
        <button type="submit" class="btn btn-primary btn-black">Подобрать</button>
    </div>
</div>
</body>
</html>
