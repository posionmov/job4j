package ru.job4j.users;

import com.google.gson.Gson;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Класс, наследующий класс HttpServlet
 * Выступает в роли сервлета
 * Основная задача данного класса - открывать таблицу со всеми пользователями
 * Около каждого пользователя должны быть кнопки (если админ - то каждому пользователю, если простой юзер, то только себе):
 * - редактировать
 * - удалить
 *
 * @author Galanov Sergey
 * @version 1.2
 * @since 26.09.2018
 */
public class UsersServlet extends HttpServlet {

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от клиента приходит запрос методом Get
     * Получает коллекцию всех пользователей из хранилища и направлет их в jsp страницу в качестве аттрибута с ключем "Users",
     * все права пользователей в качестве аттрибута "Rights"
     * Так же задает в запросе аттрибут Operation со значением show, который говорит jsp странице что нужно показать таблицу всех
     * пользователей
     * После этого направляет запрос и ответ в jsp атрницу
     *
     * @param req  - запрос от пользователя
     * @param resp - ответ пользователю
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateOut = ValidateService.INSTANCE;
        Collection<User> usersInStore = validateOut.findAll().values();
        Map<Integer, String> rgh = validateOut.getRights();


        // Создание обьекта из класса JSON обьект
        Gson usr = new Gson();
        Gson right = new Gson();
        String users = usr.toJson(usersInStore);
        String rights = right.toJson(rgh);
        req.setAttribute("usr", users);
        req.setAttribute("rgh", rights);

        req.setAttribute("Users", usersInStore);
        req.setAttribute("Rights", rgh);
        req.setAttribute("Operation", "show");
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/UsersList.jsp");
        rd.forward(req, resp);
    }

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от пользователя приходит запрос типа Post
     * Если в параметре запроса есть поле "exit" со значением "yes", то удаляет текущую сессию и записывает в ответ
     * пользователю редирект на страницу авторизации
     * При этом если в запросе пост значение параметра type ровняется delete, то вызывает метод doDelete
     *
     * @param req  - запрос пользователя
     * @param resp - ответ пользователю
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("exit").equals("yes")) {
            req.getSession().invalidate();
            resp.sendRedirect(String.format("%s/signIn", req.getContextPath()));
        } else if (req.getParameter("type").equals("delete")) {
            this.doDelete(req, resp);
        }
    }

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от пользователя приходит запрос типа Post
     * Получает из параметров запроса значение id пользователя
     * Затем производит удаление пользователя с таким id из хранилища пользователей
     * После этого задает в запросе аттрибут Operation со значением delete и направляет в jsp страницу запрос и ответ пользователю
     *
     * @param req  - запрос пользователя
     * @param resp - ответ пользователю
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateOut = ValidateService.INSTANCE;
        int id = Integer.valueOf(req.getParameter("id"));
        validateOut.delete(id);
        req.setAttribute("Operation", "delete");
        req.getRequestDispatcher("WEB-INF/views/UsersList.jsp").forward(req, resp);
        return;
    }
}
