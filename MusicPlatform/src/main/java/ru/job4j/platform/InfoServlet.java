package ru.job4j.platform;

import org.json.JSONObject;
import ru.job4j.model.User;
import ru.job4j.storage.StoreEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Сервлет для получения данных о конкретном пользователе
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public class InfoServlet extends HttpServlet {

    /**
     * Метод, отрабатывающий когда на адрес данного сервлета приходит запрос типа GET
     * Возращает данные о пользователе, всех правах доступа, адресах, типах музыки, текущего id сессии и текущих прав доступа сессии
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        int id = Integer.valueOf(req.getParameter("id"));
        Map<Integer, String> musicTypes = StoreEnum.INSTANCE.getAllTypes();
        User user = StoreEnum.INSTANCE.getUserById(id);
        Map<Integer, String> roles = StoreEnum.INSTANCE.getAllRoles();
        Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> addresses = StoreEnum.INSTANCE.getAllAddresses();
        resp.getWriter().append(new JSONObject().put("user", new JSONObject(user))
                                                .put("roles", roles)
                                                .put("addresses", addresses)
                                                .put("types", musicTypes)
                                                .put("curId", req.getSession().getAttribute("id"))
                                                .put("curRule", req.getSession().getAttribute("role")).toString()).flush();
    }
}
