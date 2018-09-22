package ru.job4j.users;

import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Класс, наследующий класс HttpServlet
 * Выступает в роли сервлета
 * Основная задача данного класса - Открывать таблицу со всеми пользователями
 * Около каждого пользователя должны быть кнопки: - редактировать
 *                                                - удалить
 * @author Galanov Sergey
 * @since 22.09.2018
 * @version 1.1
 */
public class UsersServlet extends HttpServlet {

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от клиента приходит запрос методом Get
     * Получает коллекцию всех пользователей из хранилища и направлет их в jsp страницу в качестве аттрибута с ключем "Users"
     * Так же задает в запросе аттрибут Operation со значением show, который говорит jsp странице что нужно показать таблицу всех
     *      пользователей
     * После этого направляет запрос и ответ в jsp атрницу
     * @param req - запрос от пользователя
     * @param resp - ответ пользователю
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateService = ValidateService.INSTANCE;
        Collection<User> usersInStore = validateService.findAll().values();
        req.setAttribute("Users", usersInStore);
        req.setAttribute("Operation", "show");
        req.getRequestDispatcher("WEB-INF/views/UsersList.jsp").forward(req, resp);
    }

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от пользователя приходит запрос типа Post
     * При этом если в запросе пост значение параметра type ровняется delete, то вызывает метод doDelete
     * @param req - запрос пользователя
     * @param resp - ответ пользователю
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("type").equals("delete")) {
            req.setAttribute("Operation", "show");
            this.doDelete(req, resp);
        }
    }

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от пользователя приходит запрос типа Post
     * Получает из параметров запроса значение id пользователя
     * Затем производит удаление пользователя с таким id из хранилища пользователей
     * После этого задает в запросе аттрибут Operation со значением delete и направляет в jsp страницу запрос и ответ пользователю
     * @param req - запрос пользователя
     * @param resp - ответ пользователю
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateService = ValidateService.INSTANCE;
        int id = Integer.valueOf(req.getParameter("id"));
        validateService.delete(id);
        req.setAttribute("Operation", "delete");
        req.getRequestDispatcher("WEB-INF/views/UsersList.jsp").forward(req, resp);
    }
}
