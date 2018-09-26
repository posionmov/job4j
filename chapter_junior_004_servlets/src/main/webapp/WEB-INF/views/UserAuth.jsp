<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 26.09.2018
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<table border="1" cellpadding="6">
    <form method="post">
    <tr><td align="center">Авторизация</td></tr>
    <tr><td align="right">Логин: <input type="text" name="login"></td></tr>
    <tr><td align="right">Пароль: <input type="text" name="password"></td></tr>
    <tr><td align="center">
            <input type="hidden" name="operation" value="signIn">
            <input type="submit" value="Войти">
        </form>
        <form method="post">
            <input type="hidden" name="operation" value="signUp">
            <input type="submit" value="Зарегистрироваться">
    </td></tr>
    </form>
</table>
</body>
</html>
