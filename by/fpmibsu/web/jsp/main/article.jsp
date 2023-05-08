<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.05.2023
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Cтатья</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/article.css">
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
<button class="btn btn-primary back-btn">
    <a href="/main/1">&#8592; Назад</a>
</button>
<div class="article-wrapper">
    <h3 id="article-title">Бег во время беременности: за и против</h3>
    <div class="author">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill"
             viewBox="0 0 16 16">
            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
        </svg>
        <h6 id="article-author"> Автор</h6>
    </div>
    <div id="article-categories"><h6>Категории, Категории, Категории, Категории, Категории,</h6></div>
    <div id="article-text"> Многие современные девушки на постоянной основе практикуют спортивные нагрузки: тренажерный
        зал, домашние занятия, утренние пробежки. Это быстро входит в привычку, принося в жизнь дополнительное ощущение
        бодрости и уверенности в себе. Но при наступлении беременности приходится пересматривать как образ жизни, так и
        распорядок дня, чтобы не навредить своему будущему ребёнку. При этом одним из наиболее спорных моментов по
        поводу уместности спортивных упражнений остается такая физическая активность как бег.
        Врачи давно отметили, что женщины, находящиеся в хорошей физической форме значительно лучше остальных переносят
        беременность и роды: у них легче проходит и сам период ожидания ребёнка, и схватки. А после родовой деятельности
        реже развиваются осложнения. Поэтому, если будущая мама и до беременности регулярно занималась спортом, то ей не
        стоит отказываться от активного образа жизни.
    </div>
</div>
</body>
</html>
