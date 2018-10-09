<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 26.09.2018
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Создание пользователя</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        .createUser {
            width: 35%;
            position: absolute;
            top: 45%;
            left: 50%;
            margin-right: -35%;
            transform: translate(-45%, -45%);
        }
    </style>

    <script>
        // var rights; // Переменная, хранящая в себе все права пользователей

        $.ajax({
            url: "./create",
            method: 'get',
            complete: function (data) {
                var rightsForUsers = '${rgh}';  // Получение значения (JSON из мапы прав пользователей) из АТТРИБУТА
                var rights = JSON.parse(rightsForUsers); // Парсинг значений (права пользователей)

                // console.log(rights);

                var select = document.getElementById("userRight");

                for (var key in rights) {
                    select.options[key - 1] = new Option(rights[key], key);
                }
            }
        });

        function createUser() {
            var name = $('#name').val();
            var login = $('#login').val();
            var password = $('#password').val();
            var email = $('#email').val();
            var right = $('#userRight').val();
            // alert(name + login + password + email + right);  //все верно

            $.ajax({
                url: "./create",
                type: 'post',
                data: {
                    // name: name,
                    // login: login,
                    // password: password,
                    // email: email,
                    // right: right

                    name: "q",
                    login: "q",
                    password: "q",
                    email: "q",
                    right: "1"


                },
                dataType: 'json',
                complete: function (data) {
                    var success = '${Operation}';
                    console.log(success);
                }
            });

        }

    </script>

</head>
<body>

<form class="form-horizontal createUser">
    <%--Имя--%>
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Имя:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" placeholder="Введите имя" id="name">
        </div>
    </div>
    <%--Логин--%>
    <div class="form-group">
        <label class="control-label col-sm-2" for="login">Логин:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" placeholder="Введите логин" id="login">
        </div>
    </div>
    <%--Пароль--%>
    <div class="form-group">
        <label class="control-label col-sm-2" for="password">Пароль:</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" placeholder="Введите пароль" id="password">
        </div>
    </div>
    <%--Email--%>
    <div class="form-group">
        <label class="control-label col-sm-2" for="email">Эл. почта:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" placeholder="Введите Вашу электронную почту" id="email">
        </div>
    </div>

    <div>
        <select class="form-control" id="userRight" name="right"></select>
    </div>

    <div class="btn-group btn-group" style="
                position: relative; top: 50%;
                padding-top: 100px;
                left: 50%;
                margin-right: -50%;
                transform: translate(-50%, -50%)">
        <button type="button" class="btn btn-default" onclick="createUser();">Создать пользователя</button>
        <button type="submit" class="btn btn-default" onclick="">Назад</button> <!-- todo сделать функцию редиректа -->
    </div>


</form>

<%--<c:if test="${Operation == 'show'}">--%>
<%--<table border="1" cellpadding="6">--%>
<%--<form method="post">--%>
<%--<tr>--%>
<%--<td align="center">Создание пользователя</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td align="right">Имя: <input type="text" name="name"></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td align="right">Логин: <input type="text" name="login"></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td align="right">Пароль: <input type="text" name="password"></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td align="right">Почта: <input type="text" name="email"></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td><select name="right">--%>
<%--<c:forEach items="${rights}" var="right">--%>
<%--<option value="${right.key}"><c:out value="${right.value}"></c:out></option>--%>
<%--</c:forEach>--%>
<%--</select></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td align="center"><input type="submit" value="Создать пользователя"></td>--%>
<%--</tr>--%>
<%--</form>--%>
<%--</table>--%>
<%--</c:if>--%>
<%--<c:if test="${Operation == 'add'}">--%>
<%--<table border="1" cellpadding="6">--%>
<%--<c:if test="${adding == 'success'}">--%>
<%--<tr>--%>
<%--<td>Пользователь с id:<%=request.getAttribute("id")%> был создан</td>--%>
<%--</tr>--%>
<%--</c:if>--%>
<%--<c:if test="${adding == 'fail'}">--%>
<%--<tr>--%>
<%--<td>Такой пользователь уже существует</td>--%>
<%--</tr>--%>
<%--</c:if>--%>
<%--<form action="${pageContext.request.contextPath}/list" method="get">--%>
<%--<tr>--%>
<%--<td align="center">--%>
<%--<input type="submit" value="Вернуться к списку пользователей"></td>--%>
<%--</tr>--%>
<%--</form>--%>
<%--</table>--%>
<%--</c:if>--%>
</body>
</html>
