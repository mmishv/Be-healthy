<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%@ page import="by.fpmibsu.be_healthy.entity.Article" %>
<%@ page import="by.fpmibsu.be_healthy.entity.ArticleCategory" %>
<%--
  Created by IntelliJ IDEA.
  User: Masha
  Date: 07.05.2023
  Time: 13:26
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
    <link rel="stylesheet" href="../../css/profile.css">
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
    <style>
        .add-button {
            display: block !important;
            text-align: center;
            background-color: white !important;
            width: fit-content;
            margin: 3vh auto 0 !important;
            color: black !important;
            font-weight: bold !important;
        }

        .add-button a {
            color: black !important;
        }

        .add-button a:hover {
            color: black !important;
            text-decoration: none !important;
        }
    </style>
</head>
<body>
<jsp:directive.include file="../common/header.jsp"></jsp:directive.include>
<div class="background">
    <div style="opacity: 0.8; background: black; width: 100%; height: 100%;"></div>
</div>
<div class="wrapper" style="display: flex; height: 100%;">
    <div class="sections col-sm-2">
        <ul class="list-group">
            <li class="list-group-item">
                <a class="nav-link" href="/profile">Обо мне</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="/diary/<%=valueOf(LocalDate.now().toString())%>">
                    Приёмы пищи</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="/my_recipes/1">Мои рецепты</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="">Мои статьи</a></li>
        </ul>
    </div>
    <%
        int page_cnt = (int) request.getAttribute("page_cnt");
        int cur_page = (int) request.getAttribute("cur_page");
    %>
    <div class="col-sm-10">
        <button type="button" class="btn add-button">
            <a href="http://localhost:8081/create_recipe">Добавить статью</a></button>
        <%
            ArrayList<Article> articles = new ObjectMapper().readValue(request.getAttribute("articles").toString(),
                    new TypeReference<ArrayList<Article>>() {
                    });
            for (Article article : articles) {
        %>
        <div class="articles-wrapper" style="display: block;">
            <div class="article">
                <h3 class="title"><%=article.getTitle()%>
                </h3>
                <%
                    String category;
                    for (ArticleCategory cat : article.getCategories()) {
                        category = cat.getName().toLowerCase();
                %>
                <div class="category" style="display: inline"><%=category%>
                </div>
                <%
                    }
                %>
                <h6 class="article-text"><%=article.getFulltext()%>
                </h6>
            </div>
            <%
                }
            %>
            <nav>
                <ul class="pagination">
                    <%
                        if (cur_page > 1) {
                    %>
                    <li class="page-item">
                        <a class="page-link" href="<%=(cur_page-1)%>" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <%
                        }
                        for (int i = 1; i <= page_cnt; i++) {
                    %>
                    <li class="page-item"><a class="page-link" href="<%=i%>"><%=i%>
                    </a></li>
                    <%
                        }
                        if (cur_page < page_cnt) {
                    %>
                    <li class="page-item">
                        <a class="page-link" href="<%=(cur_page+1)%>" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                    <%
                        }
                    %>
                    %>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
