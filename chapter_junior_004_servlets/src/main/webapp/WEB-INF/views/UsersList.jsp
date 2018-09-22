<%@ page import="ru.job4j.crud.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 21.09.2018
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список всех пользователей</title>
</head>
<body>
<% if (request.getAttribute("Operation").equals("show")) {%>
<table border="1" cellpadding="6" width="100%">
    <form action="<%=request.getContextPath()%>/create" method="get">
    <tr>
        <td colspan="6" align="center">
            <input type="submit" value="Создать пользователя"/>
        </td>
    </tr>
    </form>
    <tr>
        <td align="center">Id пользователя</td>
        <td align="center">Имя пользователя</td>
        <td align="center">Логин пользователя</td>
        <td align="center">Почта пользователя</td>
        <td align="center">Дата создания пользователя</td>
        <td align="center">Методы над пользователем</td>
    </tr>
        <% Collection<User> users = (Collection<User>) request.getAttribute("Users"); %>
        <% if (users.size() > 0) { %>
            <% for (User user : users) {%>
            <tr>
                <td align="center"><%=user.getId()%></td>
                <td align="center"><%=user.getName()%></td>
                <td align="center"><%=user.getLogin()%></td>
                <td align="center"><%=user.getEmail()%></td>
                <td align="center"><%=user.getCreateDate()%></td>
                <td align="center">
                    <form action="<%=request.getContextPath()%>/edit" method="get">
                        <input type="hidden" name="id" value="<%=user.getId()%>">
                        <input type="submit" value="Редактировать пользователя">
                    </form>

                    <form action="<%=request.getContextPath()%>/list" method="post">
                        <input type="hidden" name="id" value="<%=user.getId()%>">
                        <input type="hidden" name="type" value="delete">
                        <input type="submit" value="Удалить пользователя">
                    </form>
                </td>
            </tr>
            <%}%>
        <%}%>
</table>
<%} else if (request.getAttribute("Operation").equals("delete")) {%>
    <table border="1" cellpadding="6">
        <tr><td>Пользователь с id:<%=request.getParameter("id")%> удален</td></tr>
        <form method="get">
            <tr><td align="center"><input type="submit" value="Вернуться назад"></td></tr>
        </form>
    </table>
<%}%>
</body>
</html>
