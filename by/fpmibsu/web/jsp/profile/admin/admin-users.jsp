<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.05.2023
  Time: 10:35
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
    <jsp:include page="navbar.html"/>
    <div class="col-sm-10 us">
        <h3 class="header">Список пользователей</h3>
        <div class="form-group">
            <input class="form-control" id="search" placeholder="Поиск по логину..." autocomplete="off" name="search"
                   type="text" oninput="tableSearch();">
        </div>
        <div class="scroll-table">
            <table class="table table-striped users">
                <thead>
                <tr>
                    <th scope="col" style="width: 5%;">№</th>
                    <th scope="col" style="width: 25%;">Логин</th>
                    <th scope="col" style="width: 25%;">Роль</th>
                    <th scope="col" style="width: 25%;">Имя пользователя</th>
                    <th scope="col" style="width: 20%;">Управление</th>
                </tr>
                </thead>
            </table>
            <div class="scroll-table-body">
                <table class="table table-striped users" id="table-search">
                    <thead>
                    <tr>
                        <th scope="col" style="width: 5%;"></th>
                        <th scope="col" style="width: 25%;"></th>
                        <th scope="col" style="width: 25%;"></th>
                        <th scope="col" style="width: 25%;"></th>
                        <th scope="col" style="width: 20%;"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${profiles}" var="profile" varStatus="loop">
                        <tr>
                            <td>${loop.count}</td>
                            <td class="searchable">${profile.login}</td>
                            <td>${profile.role.name}</td>
                            <td>${profile.name}</td>
                            <td>
                                <c:if test="${profile.id != my_id}">
                                    <div class="control-buttons">
                                        <button type="submit" title="Назначить администратором"
                                                onclick="changeProfileRole('${profile.id}')">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22"
                                                 fill="currentColor"
                                                 class="bi bi-person-gear" viewBox="0 0 16 16">
                                                <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm.256 7a4.474 4.474 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10c.26 0 .507.009.74.025.226-.341.496-.65.804-.918C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4s1 1 1 1h5.256Zm3.63-4.54c.18-.613 1.048-.613 1.229 0l.043.148a.64.64 0 0 0 .921.382l.136-.074c.561-.306 1.175.308.87.869l-.075.136a.64.64 0 0 0 .382.92l.149.045c.612.18.612 1.048 0 1.229l-.15.043a.64.64 0 0 0-.38.921l.074.136c.305.561-.309 1.175-.87.87l-.136-.075a.64.64 0 0 0-.92.382l-.045.149c-.18.612-1.048.612-1.229 0l-.043-.15a.64.64 0 0 0-.921-.38l-.136.074c-.561.305-1.175-.309-.87-.87l.075-.136a.64.64 0 0 0-.382-.92l-.148-.045c-.613-.18-.613-1.048 0-1.229l.148-.043a.64.64 0 0 0 .382-.921l-.074-.136c-.306-.561.308-1.175.869-.87l.136.075a.64.64 0 0 0 .92-.382l.045-.148ZM14 12.5a1.5 1.5 0 1 0-3 0 1.5 1.5 0 0 0 3 0Z"/>
                                            </svg>
                                        </button>
                                        <button type="submit" title="Удалить пользователя" name="delete"
                                                onclick="deleteProfile('${profile.id}')">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="black"
                                                 class="bi bi-trash" viewBox="0 0 16 16">
                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                                <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </c:if>
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
    function changeProfileRole(id) {
        window.location.href = '/change_role/'+parseInt(id);
    }
    function deleteProfile(id) {
        window.location.href = '/delete_profile/'+parseInt(id);
    }
</script>
</body>
</html>
