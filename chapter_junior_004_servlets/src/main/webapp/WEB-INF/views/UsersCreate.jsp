<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 26.09.2018
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Создание пользователя</title>
</head>
<body>
<c:if test="${Operation == 'show'}">
    <table border="1" cellpadding="6">
        <form method="post">
            <tr>
                <td align="center">Создание пользователя</td>
            </tr>
            <tr>
                <td align="right">Имя: <input type="text" name="name"></td>
            </tr>
            <tr>
                <td align="right">Логин: <input type="text" name="login"></td>
            </tr>
            <tr>
                <td align="right">Пароль: <input type="text" name="password"></td>
            </tr>
            <tr>
                <td align="right">Почта: <input type="text" name="email"></td>
            </tr>
            <tr>
                <td><select name="right">
                    <c:forEach items="${rights}" var="right">
                        <option value="${right.key}"><c:out value="${right.value}"></c:out></option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td align="center"><input type="submit" value="Создать пользователя"></td>
            </tr>
        </form>
    </table>
</c:if>
<c:if test="${Operation == 'add'}">
    <table border="1" cellpadding="6">
        <c:if test="${adding == 'success'}">
            <tr>
                <td>Пользователь с id:<%=request.getAttribute("id")%> был создан</td>
            </tr>
        </c:if>
        <c:if test="${adding == 'fail'}">
            <tr>
                <td>Такой пользователь уже существует</td>
            </tr>
        </c:if>
        <form action="${pageContext.request.contextPath}/list" method="get">
            <tr>
                <td align="center">
                    <input type="submit" value="Вернуться к списку пользователей"></td>
            </tr>
        </form>
    </table>
</c:if>
</body>
</html>
