<%@ page import="by.fpmibsu.be_healthy.entity.Product" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%@ page import="by.fpmibsu.be_healthy.entity.Ingredient" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05.05.2023
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Микшер</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/product.css">
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
    <div class="background-cover"></div>
</div>
<div>
    <button onclick="history.back();" class="btn btn-primary back-btn">
        &#8592; Назад
    </button>
    <h3 class="mixer-header">Микшер рецептов: результат вашего запроса</h3>
</div>
<%
    ArrayList<Ingredient> prods = new ObjectMapper().readValue(request.getAttribute("products").toString(),
            new TypeReference<ArrayList<Ingredient>>() {
            });
    request.setAttribute("products", prods);
%>
<div class="table-wrapper">
    <table class="table">
        <thead>
        <tr>
            <th scope="col" style="width: 27%;">Продукт</th>
            <th scope="col" style="width: 13%;">Белки, гр.</th>
            <th scope="col" style="width: 13%;">Жиры, гр.</th>
            <th scope="col" style="width: 14%;">Углеводы, гр.</th>
            <th scope="col" style="width: 20%;">Количество, гр.</th>
            <th scope="col" style="width: 13%;">Калории</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="p">
            <tr>
                <td style="text-align: left; padding-left: 2%;">${p.name}</td>
                <td>${p.proteins}</td>
                <td>${p.fats}</td>
                <td>${p.carbohydrates}</td>
                <td>${p.quantity}</td>
                <td><fmt:formatNumber value="${p.calories * p.quantity / 100}" maxFractionDigits="1"/></td>
            </tr>
        </c:forEach>
        <tr>
            <td style=" background-color: #87CEFA; text-align: left; padding-left: 2%;">Итого</td>
            <td>${b}</td>
            <td>${j}</td>
            <td>${u}</td>
            <td>${weight}</td>
            <td>${k}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
