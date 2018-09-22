<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 21.09.2018
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование пользователя с id:<%=request.getAttribute("id")%></title>
</head>
<body>

    <% if (request.getAttribute("Operation").equals("updating")) {%>
        <table border="1" cellpadding="6">
            <tr><td align="center">Пользователь с id:<%=request.getAttribute("id")%> изменен.</td></tr>
            <form action="<%=request.getContextPath()%>/list" method="get">
            <tr><td align="center">
                    <input type="submit" value="Вернуться к списку пользователей">
                </form>
            </td></tr>
        </table>
    <%} else if (request.getAttribute("Operation").equals("show")) {%>
    <table border="1" cellpadding="6">
        <form method="post">
            <tr><td align="right">Редактирование пользователя с id:<%=request.getParameter("id")%></td></tr>
            <tr><td align="right">Имя: <input type="text" name="name" value="<%=request.getAttribute("name")%>"></td></tr>
            <tr><td align="right">Логин: <input type="text" name="login" value="<%=request.getAttribute("login")%>"></td></tr>
            <tr><td align="right">Почта: <input type="text" name="email" value="<%=request.getAttribute("email")%>"></td></tr>
            <tr><td align="center"><input type="submit" value="Сохранить изменения пользователя"></td></tr>
        </form>
    </table>
    <%}%>
</body>
</html>
