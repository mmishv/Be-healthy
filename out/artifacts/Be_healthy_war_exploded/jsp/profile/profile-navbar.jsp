<%@ page import="java.time.LocalDate" %>
<%@ page import="static java.lang.String.valueOf" %>
<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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