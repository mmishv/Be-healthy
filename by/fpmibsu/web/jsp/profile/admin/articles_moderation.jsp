<%@ page import="by.fpmibsu.be_healthy.service.ProfileService" %><%--
  Created by IntelliJ IDEA.
  User: Masha
  Date: 13.05.2023
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Режим администратора</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../assets/favicon2.png" type="image/x-icon">
    <link rel="stylesheet" href="../../css/admin.css">
    <link rel="stylesheet" href="../../css/profile.css">
    <link rel="stylesheet" href="../../../css/main.css">
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
    <div class="col-sm-10 us">
        <h3 class="header">На модерации</h3>
        <div class="articles-wrapper col-sm-5">
            <c:forEach items="${articles}" var="article" varStatus="loop">
                <div class="art-back">
                    <div class="article">
                        <h3 class="title"><a href="/article/${article.id}">${article.title}
                        </a>
                        </h3>
                        <c:forEach items="${article.categories}" var="cat" varStatus="loop">

                            <div class="category" style="display: inline">${cat.name.toLowerCase()}
                            </div>
                        </c:forEach>
                        <h6 class="article-text">${article.fulltext}
                        </h6>
                        <button type="button" class="btn" onclick="deleteArticle(${article.id}, ${cur_page})">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                 class="bi bi-bookmark-dash" viewBox="0 0 16 16">
                                <path fill-rule="evenodd"
                                      d="M5.5 6.5A.5.5 0 0 1 6 6h4a.5.5 0 0 1 0 1H6a.5.5 0 0 1-.5-.5z"/>
                                <path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
                            </svg>
                        </button>
                        <form method="post" action="articles_moderation/${cur_page}-${article.id}"
                              style="display: inline-block">
                            <button type="submit" class="btn">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                     class="bi bi-bookmark-check" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M10.854 5.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
                                    <path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
                                </svg>
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
            <nav>
                <ul class="pagination justify-content-center">
                    <c:if test="${cur_page>1}">
                        <li class="page-item">
                            <a class="page-link" href="main/${cur_page-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${page_cnt}" varStatus="i">
                        <li class="page-item"><a class="page-link" href="main/${i.count}">${i.count}
                        </a></li>
                    </c:forEach>
                    <c:if test="${cur_page < page_cnt}">

                        <li class="page-item">
                            <a class="page-link" href="main/${cur_page+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>
</div>
<script>
    function deleteArticle(id, page) {
        window.location.href = '/delete_article/' + parseInt(id) + '_admin_moderate_' + parseInt(page);
    }
</script>
</body>
</html>
