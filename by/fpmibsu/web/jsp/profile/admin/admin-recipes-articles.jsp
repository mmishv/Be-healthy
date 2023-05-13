<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 13.05.2023
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <title>Режим администратора</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/admin.css">
    <link rel="stylesheet" href="../../css/profile.css">
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
            crossorigin="anonymous">
    </script>
</head>
<body>
<jsp:directive.include file="../../common/header.jsp"></jsp:directive.include>
<div class="background">
    <div style="opacity: 0.8; background: black; width: 100%; height: 100%;"></div>
</div>
<div class="wrapper row">
    <div class="sections col-sm-2">
        <ul class="list-group">
            <li class="list-group-item">
                <button class="btn btn-primary back-btn" id="admin-btn">
                    <a href="admin">
                        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor"
                             class="bi bi-box-arrow-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
                                  d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0v2z"/>
                            <path fill-rule="evenodd"
                                  d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"/>
                        </svg>
                        Выйти</a>
                </button>
            </li>
            <li class="list-group-item">
                <a class="nav-link" href="admin">Пользователи</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="">База продуктов</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="recipes_management">Рецепты</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="">Категории рецептов</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="articles_management">Статьи</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="">Категории статей</a></li>
            <li class="list-group-item">
                <a class="nav-link" href="recipes_moderation/1">Модерация рецептов</a></li>
        </ul>
    </div>
    <div class="col-sm-10 rec">
        <c:if test="${fn:length(items) > 0}">
            <c:set var="classpath" scope="session" value="${fn:split(items[0].getClass().name.toLowerCase(), '.')}"/>
            <c:set var="cl" scope="session" value="${classpath[fn:length(classpath)-1]}"/>
            <h3 class="header">Список <c:if test="${cl == 'recipe'}">
                рецептов</c:if><c:if test="${cl == 'article'}">статей</c:if></h3>
        </c:if>
        <div class="form-group">
            <input class="form-control" id="search" placeholder="Поиск по названию ..." autocomplete="off"
                   name="search" type="text" oninput="tableSearch();">
        </div>
        <div class="scroll-table">
            <table class="table table-striped recipes">
                <thead>
                <tr>
                    <th scope="col" style="width: 10%;">№</th>
                    <th scope="col" style="width: 32%;">Название</th>
                    <th scope="col" style="width: 40%;">Дата публикации</th>
                    <th scope="col" style="width: 18%;">Управление</th>
                </tr>
                </thead>
            </table>
            <div class="scroll-table-body">
                <table class="table table-striped recipes" id="table-search">
                    <thead>
                    <tr>
                        <th scope="col" style="width: 10%;">№</th>
                        <th scope="col" style="width: 32%;">Название</th>
                        <th scope="col" style="width: 40%;">Дата публикации</th>
                        <th scope="col" style="width: 17%;">Управление</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${items}" var="item" varStatus="loop">
                        <tr>
                            <td>${loop.count}</td>
                            <td class="searchable">${item.title}</td>
                            <td>${item.dateOfPublication}</td>
                            <td>
                                <div class="control-buttons">
                                    <button type="submit" title="Редактировать" onclick="editEntity('${cl}', '${item.id}')">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                             fill="currentColor"
                                             class="bi bi-pencil" viewBox="0 0 16 16">
                                            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                    <button type="submit" title="Удалить" name="delete" onclick="deleteEntity('${cl}', '${item.id}')">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="black"
                                             class="bi bi-trash" viewBox="0 0 16 16">
                                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                                        </svg>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            </table>
        </div>
    </div>
</div>
<script src="../../../js/tableSearch.js"></script>
<script>
    function editEntity(entity, id) {
        window.location.href = '/edit_' + entity + '/' + parseInt(id)+'_admin';
    }

    function deleteEntity(entity, id) {
        window.location.href = '/delete_' + entity + '/' + parseInt(id)+'_admin';
    }
</script>
</body>
</html>