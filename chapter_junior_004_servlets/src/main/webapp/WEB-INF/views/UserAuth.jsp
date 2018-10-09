<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 26.09.2018
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .login {
            width: 35%;
            position: absolute;
            top: 45%;
            left: 50%;
            margin-right: -35%;
            transform: translate(-45%, -45%);
        }
    </style>

    <script>
        /**
         * Метод проверки того, что логин и пароль введены
         * @returns [] - массив [все введено(boolean), ассоциативный массив заполненных полей]
         */
        function checkLoginAndPassword() {
            var isCorrect = false;
            var login = $('#login').val(); <!-- Получение знаения из поля с id="login" -->
            var password = $('#password').val(); <!-- Получение знаения из поля с id="password" -->
            var emptyFields = []; <!-- Создание пустого простого массива, который будет содержать в себе ключи неаполненных полей -->
            var enteredFields = {"Логин": login, "Пароль": password}; <!-- Ассоциативный массив ключей и значений-->
            for (var value in enteredFields) {                  <!-- value - ключ && array[value] - значение -->
                if (enteredFields[value] === '') { <!-- Если значение по ключу равно нулю (пустой строке) -->
                    emptyFields.push(value); <!-- Добавление в простой массив ключа -->
                }
            }
            if (emptyFields.length > 0) {
                var forgotten = "Вы забыли ввести данные:\n";
                for (var i = 0; i < emptyFields.length; i++) {
                    forgotten += (i + 1) + ": " + emptyFields[i] + "\n";
                }
            } else {
                isCorrect = true;
            }
            return [isCorrect, enteredFields];
        }

        /**
         * Метод проверки логина и пароля, а также редирект на страницу всех пользователей
         */
        function checkUser() {
            var result = checkLoginAndPassword();
            $.ajax({
                // url: "signIn",
                url: document.location, // текущий адрес (те отправляет в сервлет)
                method: 'post',
                data: {
                    "operation": "signIn",
                    "login": result[1]["Логин"],
                    "password": result[1]["Пароль"]
                },
                complete : function(data) {
                    window.location = data.url;
                }
            });
        }

        $.ajax({
            url: document.location,
            method: 'get',
            data : {
                "operation" : "show"
            }
        });

        function redirectToRegister() {
            $.ajax({
                url: document.location,
                method: 'get',
                data: {
                    "operation": "signUp"
                },
                complete : function(data) {
                    var redirectFromServlet = '${redirect}';
                    var finalUrl = JSON.parse(redirectFromServlet);
                    console.log(finalUrl);
                    window.location.replace(finalUrl['redirect']);
                }
            });
        }

    </script>
</head>
<body>

<form class="form-horizontal login">
    <%-- Логин --%>
    <div class="form-group">
        <label class="control-label col-sm-2" for="login">Логин:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" placeholder="Введите логин" id="login">
        </div>
    </div>

    <%-- Пароль --%>
    <div class="form-group">
        <label class="control-label col-sm-2" for="password">Пароль:</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="password" placeholder="Введите пароль">
        </div>
    </div>
    <%-- Кнопки входа и регистрации --%>
    <div class="btn-group btn-group" style="
        position: relative; top: 50%;
        padding-top: 20px;
        left: 50%;
        margin-right: -50%;
        transform: translate(-50%, -50%)">
        <button type="submit" class="btn btn-default" onclick="checkUser();">Войти</button>
        <button type="button" class="btn btn-default" onclick="redirectToRegister();">Зарегистрироваться</button>
    </div>
</form>
</body>
</html>
