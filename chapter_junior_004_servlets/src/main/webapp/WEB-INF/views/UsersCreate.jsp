<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 21.09.2018
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание пользователя</title>
</head>
<body>
    <% if (request.getAttribute("Operation").equals("show")) {%>
        <table border="1" cellpadding="6">
            <form method="post">
                <tr><td align="center">Создание пользователя</td></tr>
                <tr><td align="right">Имя: <input type="text" name="name"></td></tr>
                <tr><td align="right">Логин: <input type="text" name="login"></td></tr>
                <tr><td align="right">Почта: <input type="text" name="email"></td></tr>
                <tr><td align="center"><input type="submit" value="Создать пользователя"></td></tr>
            </form>
        </table>
    <%} else if (request.getAttribute("Operation").equals("add")) {%>
        <table border="1" cellpadding="6">
        <%if (request.getAttribute("adding").equals("success")) {%>
            <tr><td>Пользователь с id:<%=request.getAttribute("id")%> был создан</td></tr>
        <%} else if (request.getAttribute("adding").equals("fail")) {%>
            <tr><td>Такой пользователь уже существует</td></tr>
        <%}%>
                <form action="<%=request.getContextPath()%>/list" method="get">
                    <tr><td align="center">
                    <input type="submit" value="Вернуться к списку пользователей"></td></tr>
            </form>
        </table>
    <%}%>
</body>
</html>
