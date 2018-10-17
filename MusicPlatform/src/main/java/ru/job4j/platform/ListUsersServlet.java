package ru.job4j.platform;

import org.json.JSONObject;
import ru.job4j.model.User;
import ru.job4j.storage.StoreEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Сервлет, отвечающий за получений информации о всех пользователях
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public class ListUsersServlet extends HttpServlet {
    /**
     * Метод, отрабатывающий когда на адрес сервлета приходит запрос типа GET.
     * Направляет данные о всех пользователях, адресах, правах доступа и типов музыки.
     * Так же направляет данные о id и правах доступа из текущей сессии
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        List<User> users = StoreEnum.INSTANCE.getUsers("all", new ArrayList<>(), 0, new ArrayList<>(), "");
        Map<Integer, String> roles = StoreEnum.INSTANCE.getAllRoles();
        Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> addresses = StoreEnum.INSTANCE.getAllAddresses();
        resp.getWriter().append(new JSONObject().put("users", users)
                                                .put("roles", roles).put("addresses", addresses)
                                                .put("curId", req.getSession().getAttribute("id"))
                                                .put("curRule", req.getSession().getAttribute("role")).toString()).flush();
    }

    /**
     * Метод, отрабатывающий когда на адрес сервлета приходит запрос типа POST
     * В данном случае аттрибут сессии будет приравнен к null, что приведет к перенаправлению текущего пользователя на страницу аторизации
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("login", req.getParameter("login"));
    }

    /**
     * Метод, отрабатывающий когда на адрес сервлета приходит запрос типа GET
     * Удаляет пользователя из БД
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        boolean isDeleted = StoreEnum.INSTANCE.deleteUser(req.getIntHeader("id"));
        resp.getWriter().append(new JSONObject().put("deleted", isDeleted).toString()).flush();
    }
}
