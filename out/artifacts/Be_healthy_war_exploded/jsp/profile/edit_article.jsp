<%--
  Created by IntelliJ IDEA.
  User: Masha
  Date: 11.05.2023
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="by.fpmibsu.be_healthy.entity.ArticleCategory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
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
<fmt:requestEncoding value="UTF-8"/>
<form method="post" action="edit_article/${article.getId()}" accept-charset="utf-8">
  <fieldset class="form-group">
    <div class="wrapper col-sm-9" style="margin: auto;">
      <div class="form-group row">
        <label for="article-title" class="col-sm-4 col-form-label">Название: </label>
        <div class="col-sm-8">
          <input class="form-control" name="title" id="article-title" value="${article.getTitle()}"
                 placeholder="Например, Правильное питание - верный путь к здоровью" required>
        </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-4 bold">Категории:</div>
        <div class="col-sm-8 row" style="margin: 0;">
          <c:forEach items="${categories}" var="category">
            <br/>
            <div class="form-check col-sm-6">
              <input class="form-check-input" type="checkbox" name="categories" value="${category.id}"
                     id="${category.id}" <c:if test="${a_cats.contains(category.id)}">checked</c:if>>
              <label class="form-check-label" for="${category.id}">
                <c:out value="${category.name}"/>
              </label>
            </div>
          </c:forEach>
        </div>
      </div>
      <div class="form-group row">
        <label for="article-description" class="col-sm-4 bold">Текст статьи:</label>
        <textarea class="form-control col-sm-8" name="text" id="article-description" rows="5"
                  required>${article.getFulltext()}</textarea>
      </div>
      <div class="form-group row">
        <button type="submit" class="btn btn-primary"
                style="background-color:#114630 !important; border: #114630 !important;">
          Сохранить
        </button>
        <button onclick="history.back();" class="btn btn-primary back-btn">Назад</button>
      </div>
    </div>
  </fieldset>
</form>
<script src="../../js/autoHeight.js"></script>
</body>
</html>
