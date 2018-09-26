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
</head>
<body>
<c:if test="${Operation == 'show'}">
    <table border="1" cellpadding="6" width="100%">
        <form action="${pageContext.request.contextPath}/create" method="get">
            <tr>
                <td colspan="7" align="center">
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
            <td align="center">Права пользователя</td>
            <td align="center">Методы над пользователем</td>
        </tr>
        <c:forEach items="${Users}" var="user">
            <tr>
                <td align="center"><c:out value="${user.id}"></c:out></td>
                <td align="center"><c:out value="${user.name}"></c:out></td>
                <td align="center"><c:out value="${user.login}"></c:out></td>
                <td align="center"><c:out value="${user.email}"></c:out></td>
                <td align="center"><c:out value="${user.createDate}"></c:out></td>
                <td align="center">
                    <c:forEach items="${Rights}" var="right">
                        <c:if test="${right.key == user.right}">
                            <c:out value="${right.value}"></c:out>
                        </c:if>
                    </c:forEach>
                </td>
                <td align="center">
                    <c:if test="${(sessionScope.right != 'user') || (sessionScope.login == user.login)}">
                        <form action="${pageContext.request.contextPath}/edit" method="get">
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="submit" value="Редактировать пользователя">
                        </form>
                        <form action="${pageContext.request.contextPath}/list" method="post">
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="hidden" name="type" value="delete">
                            <input type="hidden" name="exit" value="no">
                            <input type="submit" value="Удалить пользователя">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        <form method="post">
        <tr>
            <td colspan="7" align="center">
                <input type="hidden" name="exit" value="yes">
                <input type="submit" value="Выйти">
                </form>
            </td>
        </tr>
    </table>
</c:if>
<c:if test="${Operation == 'delete'}">
    <table border="1" cellpadding="6">
        <tr>
            <td>Пользователь с id:<c:out value="${param.id}"></c:out> удален</td>
        </tr>
        <form method="get">
            <tr>
                <td align="center"><input type="submit" value="Вернуться назад"></td>
            </tr>
        </form>
    </table>
</c:if>
</body>
</html>
