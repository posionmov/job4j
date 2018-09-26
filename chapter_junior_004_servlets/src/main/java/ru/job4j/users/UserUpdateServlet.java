package ru.job4j.users;

import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Класс-сервлет, отвечающий за обновление данных пользователя
 *
 * @author Galano Sergey
 * @version 1.1
 * @since 26.09.2018
 */
public class UserUpdateServlet extends HttpServlet {

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от пользователя приходит запрос типа Post
     * Создает пользователя на основе полученного id из формы в UsersServlet
     * Затем задает значения каждого поля в параметры запроса, а так же задает аттрибут Operation со значением show
     * После этого направляет в jsp страницу запрос и ответ пользователю
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateOut = ValidateService.INSTANCE;
        User user = validateOut.findById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("name", user.getName());
        req.setAttribute("password", user.getPassword());
        req.setAttribute("login", user.getLogin());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("rights", validateOut.getRights());
        req.setAttribute("Operation", "show");
        req.getRequestDispatcher("WEB-INF/views/UserUpdate.jsp").forward(req, resp);
    }

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от пользователя приходит запрос типа Post
     * Изменяет значение полй пользователя и перенаправляет на jsp страницу
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateOut = ValidateService.INSTANCE;
        HttpSession session = req.getSession();
        Map<Integer, String> rights = validateOut.getRights();
        int id = Integer.valueOf(req.getParameter("id"));
        User user = validateOut.findById(id);
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        if (req.getSession().getAttribute("right").equals("admin")) {
            user.setRight(Integer.valueOf(req.getParameter("right")));
            if (!session.getAttribute("right").equals(rights.get(Integer.valueOf(req.getParameter("right"))))) {
                session.setAttribute("right", rights.get(Integer.valueOf(req.getParameter("right"))));
            }
        }
        validateOut.update(id, user);
        req.setAttribute("Operation", "updating");
        req.setAttribute("id", user.getId());
        req.getRequestDispatcher("WEB-INF/views/UserUpdate.jsp").forward(req, resp);
    }
}

