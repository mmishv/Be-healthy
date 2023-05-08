<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.05.2023
  Time: 0:34
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
<div class="wrapper row">
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
                <a class="nav-link" href="/my_articles/1">Мои статьи</a></li>
        </ul>
    </div>
    <div class="col-sm-10">
        <div class="inf">
            <form>
                <div class="row general-inf">
                    <img src="./assets/profile.jpg" class="photo">
                    <div class="col-sm-8"
                         style="display: inline-flex; justify-content: space-between; padding-right: 0">
                        <h2>George</h2>
                        <button type="button" class="btn btn-primary edit-btn" data-toggle="modal"
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
                        <legend class="col-form-label col-sm-4 pt-0">Пол:</legend>
                        <div class="col-sm-8">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input large" type="radio" name="gridRadios" id="gender"
                                       value="option1" checked>
                                <label class="form-check-label" for="gender">
                                    Женский
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input large" type="radio" name="gridRadios" id="gender2"
                                       value="option2">
                                <label class="form-check-label" for="gender2">
                                    Мужской
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="form-group row">
                    <label for="height" class="col-sm-4 col-form-label">Рост:</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="height" placeholder="Рост, см" type="number">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="weight" class="col-sm-4 col-form-label">Вес:</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="weight" placeholder="Вес" type="number">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="age" class="col-sm-4 col-form-label">Возраст:</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="age" placeholder="Возраст" type="number">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="activity" class="col-sm-4 col-form-label">Физическая активность</label>
                    <select id="activity" class="form-control col-sm-7" name="activity">
                        <option value="1" selected>Без учета физ. нагрузки</option>
                        <option value="1.2">Сидячий образ жизни</option>
                        <option value="1.375">Легкая активность(1-2 раза в неделю)</option>
                        <option value="1.55">Умеренная активность (3-5 раз в неделю)</option>
                        <option value="1.725">Высокая активность (более 5 раз в неделю)</option>
                        <option value="1.9">Очень высокая активность (профессиональный спорт)</option>
                    </select>
                </div>

                <div class="form-group row">
                    <label for="aim" class="col-sm-4 col-form-label">Цель</label>
                    <select id="aim" class="form-control col-sm-7">
                        <option selected>Поддержание веса</option>
                        <option>Набор веса</option>
                        <option>Сброс веса</option>
                    </select>
                </div>

                <div class="form-group row">
                    <h5 class="col-sm-12">Задать ограничения на суточную норму</h5>
                    <label for="k" class="col-sm-3 col-form-label center">Калории, г</label>
                    <label for="b" class="col-sm-3 col-form-label center">Белки, г</label>
                    <label for="j" class="col-sm-3 col-form-label center">Жиры, г</label>
                    <label for="u" class="col-sm-3 col-form-label center">Углеводы, г</label>
                    <div class="col-sm-3">
                        <input class="form-control" id="k" type="number">
                    </div>

                    <div class="col-sm-3">
                        <input class="form-control" id="b" type="number">
                    </div>

                    <div class="col-sm-3">
                        <input class="form-control" id="j" type="number">
                    </div>

                    <div class="col-sm-3">
                        <input class="form-control" id="u" type="number">
                    </div>
                </div>
                <h6 class="col-sm-12">* рекомедуется: 1456 ккал, 82/54/190</h6>
                <div class="form-group row" style="justify-content: end;">
                    <div class="col-sm-5">
                        <button class="btn btn-primary btn-black save-btn">Сохранить</button>
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
            <form>
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
                            <input type="file" class="form-control-file" id="user-photo">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="user-name" class="col-sm-5 col-form-label">Новое имя: </label>
                        <div class="col-sm-7">
                            <input class="form-control" id="user-name" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="close-btn">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
