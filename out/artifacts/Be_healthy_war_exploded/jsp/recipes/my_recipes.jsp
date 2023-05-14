<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="../../css/recipe.css">
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
<div class="page-recipe">
    <jsp:include page="../profile/profile-navbar.jsp"/>
    <div class="col-sm-10">
        <button type="button" class="btn add-button scaled">
            <a href="http://localhost:8081/create_recipe">Добавить рецепт</a></button>
        <nav>
            <ul class="pagination">
                <c:if test="${cur_page>1}">
                    <li class="page-item">
                        <a class="page-link" href="${pref}${cur_page-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${page_cnt}" varStatus="i">
                    <li class="page-item"><a class="page-link" href="${pref}${i}"> ${i.count} </a></li>
                </c:forEach>
                <c:if test="${cur_page<page_cnt}">
                    <li class="page-item">
                        <a class="page-link" href="${pref}${cur_page+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <div class="recipe-wrapper">
            <c:forEach items="${recipes}" var="recipe" varStatus="loop">
                <div class="card">
                    <img src="data:image/jpeg;base64,${recipe.getBase64image()}" class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${recipe.title}
                        </h5>
                        <h6 class="card-title">${authors[loop.index]}</h6>
                        <h6 class="card-title">${recipe.cookingTime} минут</h6>
                    </div>
                    <div class="card-body">
                        <button type="button" class="btn scaled" onclick="deleteRecipe(${recipe.getId()})">
                            <svg xmlns="http://www.w3.org/2000/svg" width="21" height="21" fill="black"
                                 class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                            </svg>
                        </button>
                        <button type="button" class="btn btn-primary scaled" data-toggle="modal"
                                data-target="#${recipe.getId()}">
                            Посмотреть рецепт
                        </button>
                        <button type="button" class="btn scaled" onclick="editRecipe(${recipe.getId()})">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                                 class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                            </svg>
                        </button>
                    </div>
                    <div class="modal fade" id="${recipe.id}" tabindex="-1" role="dialog"
                         aria-labelledby="${recipe.id}" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title" id="${recipe.id}">${recipe.title}</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <img src="data:image/jpeg;base64,${recipe.getBase64image()}" class="modal-img">
                                    <div class="general-info">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th scope="col" class="col-md-3">К</th>
                                                <th scope="col" class="col-md-3">Б</th>
                                                <th scope="col" class="col-md-3">Ж</th>
                                                <th scope="col" class="col-md-3">У</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>${recipe.getKBJU()['k']}
                                                </td>
                                                <td>${recipe.getKBJU()['b']}
                                                </td>
                                                <td>${recipe.getKBJU()['j']}
                                                </td>
                                                <td>${recipe.getKBJU()['u']}
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="author${recipe.id}">Автор: ${authors[loop.index]}
                                        </div>
                                        <div class="cooking-time${recipe.id}">Время
                                            приготовления: ${recipe.cookingTime} минут
                                        </div>
                                        <div class="recipe-category${recipe.id}" style="display: inline">
                                            Категории:
                                        </div>
                                        <c:forEach items="${recipe.categories}" var="cat" varStatus="loop1">
                                            <div class="category" style="display: inline">${cat.name.toLowerCase()}<c:if
                                                    test="${loop1.count!=fn:length(recipe.categories)}">, </c:if></div>
                                        </c:forEach>
                                    </div>
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col"></th>
                                            <th scope="col" class="col-md-7">Ингредиент</th>
                                            <th scope="col" class="col-md-2">Количество</th>
                                            <th scope="col" class="col-md-3">Мера измерения</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${recipe.ingredients}" var="i" varStatus="loop2">
                                            <tr>
                                                <th scope="row">${loop2.count}
                                                </th>
                                                <td>${i.name}
                                                </td>
                                                <td style="text-align: center;">${i.quantity}
                                                </td>
                                                <td style="text-align: center;">${i.unit}
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <h5 style="text-align: center; margin-top: 3%">Рецепт</h5>
                                    <div id="recipe-text${recipe.id}" class="recipe-text">
                                            ${recipe.text}<br>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" data-dismiss="modal">Закрыть</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    function editRecipe(id) {
        window.location.href = '/edit_recipe/' + parseInt(id);
    }

    function deleteRecipe(id) {
        window.location.href = '/delete_recipe/' + parseInt(id);
    }
</script>
</body>
</html>
