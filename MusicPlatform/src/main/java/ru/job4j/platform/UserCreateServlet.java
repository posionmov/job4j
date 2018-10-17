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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Сервлет создания нового пользователя
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * Метод, отрабатывающий когда на адрес сервлета приходит запрос типа GET.
     * Отправляет данные о правах доступах, типах музыки и адресах
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        Map<Integer, String> roles = StoreEnum.INSTANCE.getAllRoles();
        Map<Integer, String> musicTypes = StoreEnum.INSTANCE.getAllTypes();
        Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> allAddresses = StoreEnum.INSTANCE.getAllAddresses();
        resp.getWriter().append(new JSONObject().put("roles", roles).put("types", musicTypes).put("addresses", allAddresses).toString()).flush();
    }

    /**
     * Метод, отрабатывающий когда на адрес сервлета приходит запрос типа POST.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        List<Integer> musicTypes = this.stringToArray(Arrays.asList(req.getParameter("musicType").split(",")));
        List<Integer> addresses = this.stringToArray(Arrays.asList(req.getParameter("address").split(",")));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int role = Integer.valueOf(req.getParameter("role"));
        User newUser = new User(name, login, password, role);
        newUser.setAddress(addresses);
        newUser.setMusicTypes(musicTypes);
        boolean result = StoreEnum.INSTANCE.addUser(newUser);
        resp.getWriter().append(new JSONObject().put("success", result).toString()).flush();
    }

    /**
     * Приватный вспомогательный метод
     * Переводит коллекцию строк в коллекцию целочисленных значений
     * @param strings коллекция строк для перевода
     * @return коллекция целочисленны значений
     */
    private List<Integer> stringToArray(List<String> strings) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            result.add(Integer.valueOf(strings.get(i)));
        }
        return result;
    }
}
