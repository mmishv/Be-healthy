<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.05.2023
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Главная</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/style.css">
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
            crossorigin="anonymous"></script>

</head>
<body>
<jsp:directive.include file="../common/header.jsp"></jsp:directive.include>
<div class="background">
    <div class="background-cover"></div>
</div>
<div class="wrapper row">
    <jsp:include page="profile-navbar.jsp"/>
    <div class="col-sm-10">
        <div class="exit-buttons">
            <button class="btn btn-primary back-btn">
                <a href="logout">
                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor"
                         class="bi bi-box-arrow-left" viewBox="0 0 16 16">
                        <path fill-rule="evenodd"
                              d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0v2z"/>
                        <path fill-rule="evenodd"
                              d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"/>
                    </svg>
                    Выйти</a>
            </button>
            <c:if test="${profile.getRole().getName()=='администратор'}">
                <button type="button" class="btn btn-primary admin-btn scaled">
                    <a href="admin">
                        <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor"
                             class="bi bi-person-gear" viewBox="0 0 16 16">
                            <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm.256 7a4.474 4.474 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10c.26 0 .507.009.74.025.226-.341.496-.65.804-.918C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4s1 1 1 1h5.256Zm3.63-4.54c.18-.613 1.048-.613 1.229 0l.043.148a.64.64 0 0 0 .921.382l.136-.074c.561-.306 1.175.308.87.869l-.075.136a.64.64 0 0 0 .382.92l.149.045c.612.18.612 1.048 0 1.229l-.15.043a.64.64 0 0 0-.38.921l.074.136c.305.561-.309 1.175-.87.87l-.136-.075a.64.64 0 0 0-.92.382l-.045.149c-.18.612-1.048.612-1.229 0l-.043-.15a.64.64 0 0 0-.921-.38l-.136.074c-.561.305-1.175-.309-.87-.87l.075-.136a.64.64 0 0 0-.382-.92l-.148-.045c-.613-.18-.613-1.048 0-1.229l.148-.043a.64.64 0 0 0 .382-.921l-.074-.136c-.306-.561.308-1.175.869-.87l.136.075a.64.64 0 0 0 .92-.382l.045-.148ZM14 12.5a1.5 1.5 0 1 0-3 0 1.5 1.5 0 0 0 3 0Z"/>
                        </svg>
                    </a>
                </button>
            </c:if>
        </div>
        <div class="inf">
            <form method="post" action="edit_profile">
                <div class="row general-inf">
                    <c:if test="${profile.getAvatar()!=null}">
                        <img src="data:image/jpeg;base64,${profile.getBase64image()}" class="photo">
                    </c:if>
                    <c:if test="${profile.getAvatar()==null}">
                        <img src="../../assets/profile.png" class="photo">
                    </c:if>
                    <div class="col-sm-7"
                         style="display: inline-flex; justify-content: space-between; padding-right: 0">
                        <h2>${profile.getName()}</h2>
                        <button type="button" class="btn btn-primary edit-btn scaled" data-toggle="modal"
                                data-target="#editModal">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="white"
                                 class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                            </svg>
                        </button>
                    </div>
                </div>
                <fieldset class="form-group">
                    <div class="row">
                        <legend class="col-form-label col-sm-5 pt-0">Пол:</legend>
                        <div class="col-sm-7">
                            <div class="form-check form-check-inline">
                                <input name="sex" class="form-check-input large" type="radio" id="gender"
                                       value="1"
                                       <c:if test="${profile.getSex()=='женский'}">checked</c:if>>
                                <label class="form-check-label" for="gender">
                                    Женский
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input name="sex" class="form-check-input large" type="radio" id="gender2"
                                       value="2"  <c:if test="${profile.getSex() =='мужской'}"> checked </c:if>>
                                <label class="form-check-label" for="gender2">
                                    Мужской
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="form-group row">
                    <label for="height" class="col-sm-5 col-form-label">Рост:</label>
                    <div class="col-sm-7">
                        <input name="height" class="form-control" id="height" placeholder="Рост, см" type="number"
                        <c:if test="${profile.getHeight()!=0}"> value="${profile.getHeight()}"</c:if>>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="weight" class="col-sm-5 col-form-label">Вес:</label>
                    <div class="col-sm-7">
                        <input name="weight" class="form-control" step="any"
                               id="weight" placeholder="Вес" type="number"
                        <c:if test="${profile.getWeight()!=0}"> value="${profile.getWeight()}"</c:if>>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="age" class="col-sm-5 col-form-label">Возраст:</label>
                    <div class="col-sm-7">
                        <input name="age" class="form-control" id="age" placeholder="Возраст" type="number"
                        <c:if test="${profile.getAge()!=0}"> value="${profile.getAge()}"</c:if>>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="activity" class="col-sm-5 col-form-label">Физическая активность</label>
                    <select id="activity" class="form-control" name="activity">
                        <option value="1" <c:if test="${profile.getActivity()==1}"> selected</c:if>>
                            Без учета физ. нагрузки
                        </option>
                        <option value="1.2" <c:if test="${profile.getActivity()==1.2}"> selected</c:if>>
                            Сидячий образ жизни
                        </option>
                        <option value="1.375" <c:if test="${profile.getActivity()==1.375}"> selected</c:if>>
                            Легкая активность(1-2 раза в неделю)
                        </option>
                        <option value="1.55" <c:if test="${profile.getActivity()==1.55}"> selected</c:if>>
                            Умеренная активность (3-5 раз в неделю)
                        </option>
                        <option value="1.725" <c:if test="${profile.getActivity()==1.725}"> selected</c:if>>
                            Высокая активность (более 5 раз в неделю)
                        </option>
                        <option value="1.9" <c:if test="${profile.getActivity()==1.9}"> selected</c:if>>
                            Очень высокая активность (профессиональный спорт)
                        </option>
                    </select>
                </div>

                <div class="form-group row">
                    <label for="aim" class="col-sm-5 col-form-label">Цель</label>
                    <select name="aim" id="aim" class="form-control">
                        <option value="1" <c:if test="${profile.getGoal()==1}"> selected</c:if>>Поддержание веса
                        </option>
                        <option value="1.2"<c:if test="${profile.getGoal()==1.2}"> selected</c:if>>Набор веса</option>
                        <option value="0.8" <c:if test="${profile.getGoal()==0.8}"> selected</c:if>>Сброс веса</option>
                    </select>
                </div>
                <c:set var="kbju" scope="session" value="${profile.getKBJU_norm()}"/>
                <div class="form-group row">
                    <h5 class="col-sm-12">Задать ограничения на суточную норму</h5>
                    <label for="k" class="col-sm-3 col-form-label center">Калории, г</label>
                    <label for="b" class="col-sm-3 col-form-label center">Белки, г</label>
                    <label for="j" class="col-sm-3 col-form-label center">Жиры, г</label>
                    <label for="u" class="col-sm-3 col-form-label center">Углеводы, г</label>
                    <div class="col-sm-3">
                        <input name="k" class="form-control" id="k" type="number" step="any"
                               placeholder="${profile.getCalorieRec()}" value="${kbju.get('k')}">
                    </div>

                    <div class="col-sm-3">
                        <input name="b" class="form-control" id="b" type="number" step="any"
                               placeholder="${profile.getProteinRec()}" value="${kbju.get('b')}">
                    </div>

                    <div class="col-sm-3">
                        <input name="j" class="form-control" id="j" type="number" step="any"
                               placeholder="${profile.getFatRec()}" value="${kbju.get('j')}">
                    </div>

                    <div class="col-sm-3">
                        <input name="u" class="form-control" id="u" type="number" step="any"
                               placeholder="${profile.getCarbRec()}" value="${kbju.get('u')}">
                    </div>
                </div>
                <h6 class="col-sm-12">
                    <c:if test="${profile.getProteinRec() != 0}"> * рекомедуется: ${profile.getCalorieRec()} ккал,
                        ${profile.getProteinRec()}/${profile.getFatRec()}/${profile.getCarbRec()}</c:if>
                    <c:if test="${profile.getProteinRec() == 0}"> * пожалуйста, введите информацию о своем росте
                        , возрасте и весе, чтобы мы могли дать Вам рекомендации</c:if>
                </h6>
                <div class="form-group row" style="justify-content: end;">
                    <div class="col-sm-5">
                        <button class="btn btn-primary btn-black save-btn scaled">Сохранить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <fmt:requestEncoding value="UTF-8"/>
            <form method="post" action="profile" enctype="multipart/form-data" accept-charset="utf-8">
                <div class="modal-header">
                    <h4 class="modal-title">Редактировать</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body cur-products" id="cur-products">
                    <div class="form-group row">
                        <label for="user-photo" class="col-sm-5 bold">Загрузить фото</label>
                        <div class="col-sm-7">
                            <input type="file" class="form-control-file" id="user-photo" name="avatar"
                                   accept="image/png, image/jpeg">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="user-name" class="col-sm-5 col-form-label">Новое имя: </label>
                        <div class="col-sm-7">
                            <input name="name" class="form-control" id="user-name" value="${profile.getName()}">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-black btn-primary">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>