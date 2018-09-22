package ru.job4j.users;

import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс-сервлет, отвечающий за обновление данных пользователя
 * @author Galano Sergey
 * @since 22.09.2018
 * @version 1.0
 */
public class UserUpdateServlet extends HttpServlet {

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от пользователя приходит запрос типа Post
     * Создает пользователя на основе полученного id из формы в UsersServlet
     * Затем задает значения каждого поля в параметры запроса, а так же задает аттрибут Operation со значением show
     * todo Не уверен что правильно передавать каждый из значений полей пользователя
     * todo Можно передавать и всего пользователя, я не вижу особой разницы
     * После этого направляет в jsp страницу запрос и ответ пользователю
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateService = ValidateService.INSTANCE;
        User user = validateService.findById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("name", user.getName());
        req.setAttribute("login", user.getLogin());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("Operation", "show");
        req.getRequestDispatcher("WEB-INF/views/UserUpdate.jsp").forward(req, resp);
    }

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от пользователя приходит запрос типа Post
     * Изменяет значение полй пользователя и перенаправляет на jsp страницу
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateService = ValidateService.INSTANCE;
        int id = Integer.valueOf(req.getParameter("id"));
        User user = validateService.findById(id);
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        validateService.update(id, user);
        req.setAttribute("Operation", "updating");
        req.setAttribute("id", user.getId());
        req.getRequestDispatcher("WEB-INF/views/UserUpdate.jsp").forward(req, resp);
    }
}

