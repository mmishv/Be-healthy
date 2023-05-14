<%@ page import="java.util.Objects" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="by.fpmibsu.be_healthy.entity.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%--
  Created by IntelliJ IDEA.
  User: Masha
  Date: 23.04.2023
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Главная</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/main.css">
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
    <div class="background-cover"></div>
</div>
<div style="display: flex">
    <div class="calculator">
        <h2>Калькулятор калорий</h2>
        <form action="/main" method="GET">
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
                    <button type="submit" class="btn btn-primary btn-black scaled">Рассчитать</button>
                </div>
                <div class="col-sm-3">
                    <button type="reset" class="btn btn-primary scaled"
                            style="background-color: #00000000; border: solid 2px white;">Сбросить
                    </button>
                </div>
            </div>
            <c:if test="${not empty result}">
                <h5> Результат: ${result} </h5>
            </c:if>
        </form>
    </div>
    <%
        ArrayList<Article> articles = new ObjectMapper().readValue(request.getAttribute("articles").toString(),
                new TypeReference<ArrayList<Article>>() {
                });
        request.setAttribute("articles", articles);
    %>
    <div class="articles-wrapper col-sm-5">
        <c:forEach items="${articles}" var="article" varStatus="loop">
            <div class="art-back scaled">
                <div class="article">
                    <h3 class="title"><a href="/article/${article.id}">${article.title}
                    </a>
                    </h3>
                    <c:forEach items="${article.categories}" var="cat" varStatus="loop">

                        <div class="category" style="display: inline">${cat.name.toLowerCase()}
                        </div>
                    </c:forEach>
                    <h6 class="article-text">${article.fulltext}
                    </h6>
                </div>
            </div>
        </c:forEach>
        <nav>
            <ul class="pagination justify-content-center">
                <c:if test="${cur_page>1}">
                    <li class="page-item">
                        <a class="page-link" href="main/${cur_page-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${page_cnt}" varStatus="i">
                    <li class="page-item"><a class="page-link" href="main/${i.count}">${i.count}
                    </a></li>
                </c:forEach>
                <c:if test="${cur_page < page_cnt}">

                    <li class="page-item">
                        <a class="page-link" href="main/${cur_page+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>