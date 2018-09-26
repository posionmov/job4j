<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 26.09.2018
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Редактирование пользователя с id:<%=request.getAttribute("id")%>
    </title>
</head>
<body>
<c:if test="${Operation == 'updating'}">
    <table border="1" cellpadding="6">
        <tr>
            <td align="center">Пользователь с id:<c:out value="${param.id}"></c:out> изменен.</td>
        </tr>
        <form action="${pageContext.request.contextPath}/list" method="get">
            <tr>
                <td align="center">
                    <input type="submit" value="Вернуться к списку пользователей">
        </form>
        </td></tr>
    </table>
</c:if>
<c:if test="${Operation == 'show'}">
    <table border="1" cellpadding="6">
        <form method="post">
            <tr>
                <td align="right">Редактирование пользователя с id:<c:out value="${param.id}"></c:out></td>
            </tr>
            <tr>
                <td align="right">Имя: <input type="text" name="name" value="${name}"></td>
            </tr>
            <tr>
                <td align="right">Логин: <input type="text" name="login" value="${login}"></td>
            </tr>
            <tr>
                <td align="right">Почта: <input type="text" name="email" value="${email}"></td>
            </tr>
            <tr>
                <td align="center"><input type="submit" value="Сохранить изменения пользователя"></td>
            </tr>
        </form>
    </table>
</c:if>
</body>
</html>
