<%@ page import="by.fpmibsu.be_healthy.entity.Meal" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%@ page import="by.fpmibsu.be_healthy.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2023
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Дневник питания</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/diary.css">
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
</head>
<body>
<jsp:directive.include file="../common/header.jsp"></jsp:directive.include>
<div class="background">
    <div style="opacity: 0.8; background: black; width: 100%; height: 100%;"></div>
</div>
<div class="page">
    <div class="meals col-sm-9">
        <!--        !!!!!!!!!!!!!!!!!!!!!!!! add arrows          -->
        <div id="date">
            <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="#ffffff9e"
                 class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
            </svg>
            <input type="text" id="airdatepicker" class="form-control">
            <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="#ffffff9e"
                 class="bi bi-arrow-right-circle-fill" viewBox="0 0 16 16">
                <path d="M8 0a8 8 0 1 1 0 16A8 8 0 0 1 8 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/>
            </svg>
        </div>
        <% ArrayList<Meal> meals = new ObjectMapper().readValue(request.getAttribute("meals").toString(),
                new TypeReference<ArrayList<Meal>>() {
                });
            ArrayList<Product> prods = new ObjectMapper().readValue(request.getAttribute("products").toString(),
                    new TypeReference<ArrayList<Product>>() {
                    });
            request.setAttribute("products", prods);
            request.setAttribute("meals", meals );
        %>
        <div class="meals-wrapper">
            <c:forEach items="${meals}" var="meal">
                <div class="meal" id="meal${meal.id}">
                    <c:if test="${not empty meal.name}">
                    <div class="meal-name" id="meal-name1">${meal.name}</div>
                    </c:if>
                    <c:if test="${empty meal.name}">
                        <div class="meal-name" id="meal-name1">Без названия</div>
                    </c:if>
                    <div class="header product row">
                        <div class="prod-name"></div>
                        <div class="prod-bju">Б/Ж/У</div>
                        <div class="prod-k">ккал</div>
                    </div>
                    <c:forEach items="${meal.products}" var="product">
                        <div class="product row">
                            <div class="prod-name">${product.name}</div>
                            <div class="prod-bju">${product.proteins}/${product.fats}/${product.carbohydrates}</div>
                            <div class="prod-k">${product.calories}</div>
                        </div>
                    </c:forEach>
                    <div class="delete-meal">
                        <form method="get" action="/delete-meal/${meal.id}_${date}">
                            <button type="submit" class="btn btn-danger"
                                    name="delete" value="${meal.id}">Удалить</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="col-sm-3">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary add-button" data-toggle="modal" data-target="#exampleModalCenter">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                 class="bi bi-plus-square-fill" viewBox="0 0 16 16">
                <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0z"/>
            </svg>
            Добавить приём пищи
        </button>

        <div class="total">
            <div class="total-name">Отчёт дня</div>
            <div class="progress">
                <div class="progress-bar progress-bar-striped" role="progressbar"
                     style="width: 10%; background-color:#1f9f7a" aria-valuenow="10" aria-valuemin="0"
                     aria-valuemax="100"></div>
            </div>
            <div class="progress">
                <div class="progress-bar progress-bar-striped" role="progressbar"
                     style="width: 25%; background-color:#7fba52" aria-valuenow="25" aria-valuemin="0"
                     aria-valuemax="100"></div>
            </div>
            <div class="progress">
                <div class="progress-bar progress-bar-striped" role="progressbar"
                     style="width: 50%; background-color:#c2c458" aria-valuenow="50" aria-valuemin="0"
                     aria-valuemax="100"></div>
            </div>
            <div class="progress">
                <div class="progress-bar progress-bar-striped" role="progressbar"
                     style="width: 75%; background-color:#c39143" aria-valuenow="75" aria-valuemin="0"
                     aria-valuemax="100"></div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <form method="post" action="/diary/${date}" accept-charset="utf-8">
                <div class="modal-header">
                    <input class="modal-title" id="exampleModalLongTitle" name="title" placeholder="Название приёма пищи"></input>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="products">
                    <div class="row product-option" id="prod-option1">
                        <select id="product1" name="product1" class="form-control col-sm-6">
                            <c:forEach items="${products}" var="product">
                                <option value="${product.id}"><c:out value="${product.name}"/></option>
                            </c:forEach>
                        </select>
                        <input id="quantity1" name="quantity1" type="number" class="form-control col-sm-2" placeholder="кол-во" required>
                        <select id="measure1" name="measure1" class="form-control col-sm-2">
                            <option selected>гр.</option>
                            <option>мл.</option>
                        </select>
                        <button class="col-sm-1 ing-button" onclick="addProduct(this)">+</button>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="../../js/air-datepicker.js"></script>
<script>
    new AirDatepicker('#airdatepicker', {
        selectedDates: [new Date()],
        autoClose: true,
        position: 'bottom center'
    });
    var counter = 2;

    function addProduct(e) {
        if (e.classList.contains('disabled')) {
            e.parentElement.remove();
        } else {
            let container = e.parentElement.cloneNode(true);
            container.id = "prod-option" + counter;
            let ch = container.children;
            ch[0].id = "product" + counter;
            ch[0].name = "product" + counter;
            ch[1].id = "quantity" + counter;
            ch[1].name = "quantity" + counter;
            ch[1].value = '';
            ch[2].id = "measure" + counter;
            ch[2].name = "measure" + counter;
            counter++;
            document.getElementById('products').appendChild(container);
            e.classList.add('disabled');
            e.style.backgroundColor = '#114630a8';
            e.innerHTML = '-';
        }
    }
</script>
</body>
</html>