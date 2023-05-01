<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.04.2023
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Продукты</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/product.css">
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
<div class="mixer col-sm-5">
    <h3 style="text-align: center; margin-bottom: 5%;">Микшер рецептов</h3>
    <div id="products">
        <div class="product-inf d-flex center">
            <input class="col-sm-6 form-control product" placeholder="Продукт">
            <input class="col-sm-2 form-control quantity" placeholder="кол-во">
            <select class="form-control col-sm-2 measure">
                <option selected>шт.</option>
                <option>ч.л.</option>
                <option>ст.л.</option>
                <option>г</option>
                <option>мл</option>
            </select>
            <button class="col-sm-1 ing-button" onclick="addProduct(this)">+</button>
        </div>
    </div>
    <div style="display: flex; justify-content: flex-end;">
        <button type="submit" class="btn btn-primary btn-black">Подобрать</button>
    </div>
</div>
<script>
    function addProduct(e) {
        if (e.classList.contains('disabled')) {
            e.parentElement.remove();
        } else {
            e.classList.add('disabled');
            e.style.backgroundColor = '#114630a8';
            e.innerHTML = '-';
            let inputField1 = document.createElement('input');
            inputField1.className = "col-sm-6 form-control product";
            inputField1.placeholder = "Продукт";
            let inputField2 = document.createElement('input');
            inputField2.className = "col-sm-2 form-control quantity";
            inputField2.type = "number";
            inputField2.placeholder = "кол-во";
            let selectField = document.createElement('select');
            selectField.className = "form-control col-sm-2 measure";
            let options = ['шт.','ч.л.','ст.л.','г','мл'];
            for(let i = 0; i < options.length; i++){
                let opt = document.createElement('option');
                opt.innerHTML = options[i];
                if(i == 0)
                    opt.selected = true;
                selectField.appendChild(opt);
            }
            let button = document.createElement('button');
            button.className = "col-sm-1 ing-button";
            button.innerHTML = '+';
            button.addEventListener('click', () => addProduct(button));
            let container = document.createElement('div');
            container.classList.add('row');
            container.appendChild(inputField1);
            container.appendChild(inputField2);
            container.appendChild(selectField);
            container.appendChild(button);
            container.className = 'product-inf d-flex center';
            document.getElementById('products').appendChild(container);
        }
    }
</script>
</body>
</html>
