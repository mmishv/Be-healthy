<%@ page import="by.fpmibsu.be_healthy.entity.RecipeCategory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%@ page import="by.fpmibsu.be_healthy.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2023
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Главная</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/new_recipe.css">
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
<% ArrayList<RecipeCategory> cats = new ObjectMapper().readValue(request.getAttribute("categories").toString(),
        new TypeReference<ArrayList<RecipeCategory>>() {
        });
    ArrayList<Product> prods = new ObjectMapper().readValue(request.getAttribute("products").toString(),
            new TypeReference<ArrayList<Product>>() {
            });
    request.setAttribute("categories", cats);
    request.setAttribute("products", prods);
%>
<fmt:requestEncoding value="UTF-8"/>
<form method="post" action="create_recipe" enctype="multipart/form-data" accept-charset="utf-8">
    <fieldset class="form-group">
        <div class="wrapper col-sm-9" style="margin: auto;">
            <div class="form-group row">
                <label for="recipe-title" class="col-sm-4 col-form-label">Название: </label>
                <div class="col-sm-8">
                    <input class="form-control" name="title" id="recipe-title" placeholder="Например, оладьи" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="cooking-time" class="col-sm-4 col-form-label">Время приготовления, мин: </label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="cooking-time" name="cooking-time"
                           placeholder="Например, 30"
                           required>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4 bold">Категории:</div>
                <div class="col-sm-8 row" style="margin: 0;">
                    <c:forEach items="${categories}" var="category">
                        <br/>
                        <div class="form-check col-sm-6">
                            <input class="form-check-input" type="checkbox" name="categories" value="${category.id}"
                                   id="${category.id}">
                            <label class="form-check-label" for="${category.id}">
                                <c:out value="${category.name}"/>
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4 bold">Ингредиенты:</div>
                <div class="col-sm-8" id="ingredients-list">
                    <div class="row ing-option" id="ing-option1">
                        <select id="ingredient1" name="ingredient1" class="form-control col-sm-6" required>
                            <c:forEach items="${products}" var="product">
                                <option value="${product.id}"><c:out value="${product.name}"/></option>
                            </c:forEach>
                        </select>
                        <input id="quantity1" name="quantity1" type="number" class="form-control col-sm-2"
                               placeholder="кол-во" required>
                        <select id="measure1" name="measure1" class="form-control col-sm-2">
                            <option selected>г.</option>
                            <option>мл.</option>
                        </select>
                        <button class="col-sm-1 ing-button"
                                onclick="addRow(this, 'ingredients-list', 'ing-option', 'ingredient')">
                            +
                        </button>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="recipe-photo" class="col-sm-4 bold">Фото рецепта:</label>
                <div class="col-sm-8">
                    <input type="file" name="image" class="form-control-file" id="recipe-photo" required accept="image/png, image/jpeg">
                </div>
            </div>
            <div class="form-group row">
                <label for="recipe-description" class="col-sm-4 bold">Описание рецепта:</label>
                <textarea class="form-control col-sm-8" name="text" id="recipe-description" rows="5"
                          required></textarea>
            </div>
            <div class="form-group row">
                <button type="submit" class="btn btn-primary"
                        style="background-color:#114630 !important; border: #114630 !important;">
                    Добавить
                </button>
                <button onclick="history.back();" class="btn btn-primary back-btn">Вернуться к рецептам</button>
            </div>
        </div>
    </fieldset>
</form>
<script src="../../js/addRow.js"></script>
<script src="../../js/autoHeight.js"></script>
</body>
</html>
