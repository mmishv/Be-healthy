<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.05.2023
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Режим администратора</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/admin.css">
    <link rel="stylesheet" href="../../css/profile.css">
    <link rel="stylesheet" href="../../css/style.css">
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
    <div class="background-cover"></div>
</div>
<div class="wrapper row">
    <jsp:include page="navbar.html"/>
    <div class="col-sm-10 simple">
        <c:if test="${fn:length(items) > 0}">
            <c:set var="classpath" scope="session" value="${fn:split(items[0].getClass().name.toLowerCase(), '.')}"/>
            <c:set var="cl" scope="session" value="${classpath[fn:length(classpath)-1]}"/>
            <h3 class="header">
                <c:if test="${cl == 'recipecategory'}">Список категорий рецептов</c:if>
                <c:if test="${cl == 'articlecategory'}">Список категорий статей</c:if>
                <c:if test="${cl == 'product'}">База продуктов</c:if>
            </h3>
        </c:if>
        <div class="form-group">
            <button class="btn add-btn scaled" type="submit" title="Добавить" data-toggle="modal"
                    data-target="#createModal">
                +
            </button>
            <input class="form-control" id="search" placeholder="Поиск по названию..." autocomplete="off" name="search"
                   type="text" oninput="tableSearch();">
        </div>
        <div class="scroll-table">
            <table class="table table-striped simple-table">
                <thead>
                <tr>
                    <th scope="col" style="width: 10%;">№</th>
                    <th scope="col" style="width: 60%;">Название</th>
                    <th scope="col" style="width: 30%;">Управление</th>
                </tr>
                </thead>
            </table>
            <div class="scroll-table-body">
                <table class="table table-striped simple-table" id="table-search">
                    <thead>
                    <tr>
                        <th scope="col" style="width: 10%;"></th>
                        <th scope="col" style="width: 60%;"></th>
                        <th scope="col" style="width: 30%;"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${items}" var="item" varStatus="loop">
                        <tr>
                            <td>${loop.count}</td>
                            <td class="searchable">${item.name}
                                <c:if test="${cl == 'product'}">(${item.calories}/${item.proteins}/${item.fats}/${item.carbohydrates}</c:if>
                            </td>
                            <td>
                                <div class="control-buttons">
                                    <button type="submit" title="Редактировать" data-toggle="modal"
                                            data-target="#editModal${item.id}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                             fill="currentColor"
                                             class="bi bi-pencil" viewBox="0 0 16 16">
                                            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                    <button type="submit" title="Удалить" name="delete" onclick="deleteCategory('${cl.charAt(0)}', '${item.id}')">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="black"
                                             class="bi bi-trash" viewBox="0 0 16 16">
                                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                                        </svg>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <div class="modal fade col-sm-10" id="editModal${item.id}" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <form method="post" action="/edit_category/${cl.charAt(0)}-${item.id}">
                                        <div class="modal-body">
                                            <input name="name" class="form-control" value="${item.name}" placeholder="Новое название" required>
                                            <button type="submit" class="btn btn-primary save-btn">Сохранить</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            </table>
        </div>
    </div>
</div>
<c:if test="${cl == 'recipecategory' or cl == 'articlecategory'}">
<div class="modal fade col-sm-10" id="createModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <form method="post">
                <div class="modal-body">
                    <input name="name" class="form-control" placeholder="Название" required>
                    <button type="submit" class="btn btn-primary save-btn">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>
</c:if>
<script src="../../../js/tableSearch.js"></script>
<script>
    function deleteCategory(entity, id) {
        window.location.href = '/delete_category/'+entity+'-'+parseInt(id);
    }
</script>
</body>
</html>
