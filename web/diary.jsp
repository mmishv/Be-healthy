<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2023
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Дневник питания</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/diary.css">
    <link rel="stylesheet" href="css/air-datepicker.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:directive.include file="header.jsp"></jsp:directive.include>
<div class="background">
    <div style="opacity: 0.8; background: black; width: 100%; height: 100%;"></div>
</div>
<div class="page">
    <div class="meals col-sm-9">
        <!--        !!!!!!!!!!!!!!!!!!!!!!!! add arrows          -->
        <div id="date">
            <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="#ffffff9e"
                 class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
            </svg>
            <input type="text" id="airdatepicker" class="form-control">
            <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="#ffffff9e"
                 class="bi bi-arrow-right-circle-fill" viewBox="0 0 16 16">
                <path d="M8 0a8 8 0 1 1 0 16A8 8 0 0 1 8 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/>
            </svg>
        </div>
        <div class="meals-wrapper">
            <div class="meal">
                <div class="meal-name">Завтрак</div>
                <div class="col-sm-8"></div>
                <div class="col-sm-2"></div>
                <div class="col-sm-2"></div>
            </div>
            <div class="meal">
                <div class="meal-name">Обед</div>
                <div class="col-sm-8"></div>
                <div class="col-sm-2"></div>
                <div class="col-sm-2"></div>
            </div>
            <div class="meal">
                <div class="meal-name">Чаепитие</div>
                <div class="col-sm-8"></div>
                <div class="col-sm-2"></div>
                <div class="col-sm-2"></div>
            </div>
            <div class="meal">
                <div class="meal-name">Ужин</div>
                <div class="col-sm-8"></div>
                <div class="col-sm-2"></div>
                <div class="col-sm-2"></div>
            </div>
        </div>
        <div class="add-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" color="#114630" fill="currentColor"
                 class="bi bi-plus" viewBox="0 0 16 16">
                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
            </svg>
        </div>
    </div>

    <div class="total col-sm-3">
        <div class="total-name">Отчёт дня</div>
        <div class="progress">
            <div class="progress-bar progress-bar-striped" role="progressbar"
                 style="width: 10%; background-color:#1f9f7a" aria-valuenow="10" aria-valuemin="0"
                 aria-valuemax="100"></div>
        </div>
        <div class="progress">
            <div class="progress-bar progress-bar-striped" role="progressbar"
                 style="width: 25%; background-color:#7fba52" aria-valuenow="25" aria-valuemin="0"
                 aria-valuemax="100"></div>
        </div>
        <div class="progress">
            <div class="progress-bar progress-bar-striped" role="progressbar"
                 style="width: 50%; background-color:#c2c458" aria-valuenow="50" aria-valuemin="0"
                 aria-valuemax="100"></div>
        </div>
        <div class="progress">
            <div class="progress-bar progress-bar-striped" role="progressbar"
                 style="width: 75%; background-color:#c39143" aria-valuenow="75" aria-valuemin="0"
                 aria-valuemax="100"></div>
        </div>
    </div>
</div>
<script src="js/air-datepicker.js"></script>
<script>
    new AirDatepicker('#airdatepicker', {
        selectedDates: [new Date()],
        autoClose: true,
        position: 'bottom center'
    });
</script>
</body>
</html>
