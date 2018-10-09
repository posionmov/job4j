<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 26.09.2018
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Список всех пользователей</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        div.delete {
            display: none !important;    /* По дефолту скрывает некоторую часть HTML кода */
        }
    </style>

    <script>

        var usersFromServlet; // Переменная, принимающая всех юзеров из сервлета
        var rightsForUsers;

        $.ajax({
            url: document.location,
            method: 'get',
            complete: function (data) {
                usersFromServlet = '${usr}';  // Получение значения (JSON из мапы пользователей) из АТТРИБУТА
                rightsForUsers = '${rgh}';  // Получение значения (JSON из мапы прав пользователей) из АТТРИБУТА
                
                var users = JSON.parse(usersFromServlet); // Парсинг значений (пользователи)
                var rights = JSON.parse(rightsForUsers); // Парсинг значений (права пользователей)

                console.log(users);

                for (var i = 0; i < users.length; i++) {
                    $('#usersTable tbody').append('<tr>'
                        + '<td>' + users[i].id + '</td>'
                        + '<td>' + users[i].name + '</td>'
                        + '<td>' + users[i].login + '</td>'
                        + '<td>' + users[i].password + '</td>'
                        + '<td>' + users[i].email + '</td>'
                        + '<td>' + users[i].createDate + '</td>'
                        + '<td>' + rights[users[i].right] + '</td>'
                        + '<td> example </td></tr>');
                }
            }
        });

        function deleting() {
            $.ajax({
                url: document.location, // текущий адрес (те отправляет в сервлет)
                method: 'delete',
                data: {
                    "id": $('#name').val()
                },
                success: function (data) {
                    window.location = data.url;
                }
            });
        }
    </script>

</head>

<body>
<div class="container">
    <h2>Таблица всех пользователей</h2>
    <p>Полный список пользователей <kbd>в базе данных</kbd> данный момент:</p>
    <table class="table table-hover" id="usersTable">
        <thead>
            <tr>
                <th>Id пользователя</th>
                <th>Имя пользователя</th>
                <th>Логин пользователя</th>
                <th>Пароль пользователя</th>
                <th>Почта пользователя</th>
                <th>Дата создания пользователя</th>
                <th>Права пользователя</th>
                <th>Управление пользователем</th>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>


<%--<div class="show">--%>
<%--&lt;%&ndash;<c:if test="${Operation == 'show'}">&ndash;%&gt;--%>
    <%--<table class="table table-hover">--%>
        <%--<form action="${pageContext.request.contextPath}/create" method="get">--%>
            <%--<tr>--%>
                <%--<td colspan="7" align="center">--%>
                    <%--<input type="submit" class="btn btn-default" value="Создать пользователя"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</form>--%>
        <%--<tr>--%>
            <%--<td align="center">Id пользователя</td>--%>
            <%--<td align="center">Имя пользователя</td>--%>
            <%--<td align="center">Логин пользователя</td>--%>
            <%--<td align="center">Почта пользователя</td>--%>
            <%--<td align="center">Дата создания пользователя</td>--%>
            <%--<td align="center">Права пользователя</td>--%>
            <%--<td align="center">Методы над пользователем</td>--%>
        <%--</tr>--%>
        <%--<c:forEach items="${Users}" var="user">--%>
            <%--<tr>--%>
                <%--<td align="center"><c:out value="${user.id}"></c:out></td>--%>
                <%--<td align="center"><c:out value="${user.name}"></c:out></td>--%>
                <%--<td align="center"><c:out value="${user.login}"></c:out></td>--%>
                <%--<td align="center"><c:out value="${user.email}"></c:out></td>--%>
                <%--<td align="center"><c:out value="${user.createDate}"></c:out></td>--%>
                <%--<td align="center">--%>
                    <%--<c:forEach items="${Rights}" var="right">--%>
                        <%--<c:if test="${right.key == user.right}">--%>
                            <%--<c:out value="${right.value}"></c:out>--%>
                        <%--</c:if>--%>
                    <%--</c:forEach>--%>
                <%--</td>--%>
                <%--<td align="center">--%>
                    <%--<c:if test="${(sessionScope.right != 'user') || (sessionScope.login == user.login)}">--%>
                        <%--<form action="${pageContext.request.contextPath}/edit" method="get">--%>
                            <%--<input type="hidden" name="id" value="${user.id}">--%>
                            <%--<input type="submit" class="btn btn-default" value="Редактировать пользователя">--%>
                        <%--</form>--%>
                        <%--<form>--%>
                            <%--<input type="hidden" name="id" value="${user.id}">--%>
                            <%--<input type="hidden" name="type" value="delete">--%>
                            <%--<input type="hidden" name="exit" value="no">--%>
                            <%--<button type="submit" class="btn btn-default" onclick="deleting()">Удалить пользователя</button>--%>
                        <%--</form>--%>
                    <%--</c:if>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--<form method="post">--%>
        <%--<tr>--%>
            <%--<td colspan="7" align="center">--%>
                <%--<input type="hidden" name="exit" value="yes">--%>
                <%--<input type="submit" class="btn btn-default" value="Выйти">--%>
                <%--</form>--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
<%--</div>--%>




<div class="delete">
<%--<c:if test="${Operation == 'delete'}">--%>
    <table class="table table-hover">
        <tr>
            <td>Пользователь с id:<c:out value="${param.id}"></c:out> удален</td>
        </tr>
        <%--<form>--%>
            <tr>
                <td align="center">
                    <button type="submit" value="Вернуться назад"></button>
                </td>
            </tr>
        <%--</form>--%>
    </table>
<%--</c:if>--%>
</div>
</body>
</html>
