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

/**
 * Сервлет для редактирования пользователя
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public class EditServlet extends HttpServlet {

    /**
     * Метод, отрабатывающий когда на адрес данного сервлета приходит запрос метода POST
     * Обновляет пользователя по полученным данным их HTML страницы ТОЛЬКО при соблюдении одного из суловий:
     *                  1) текущая сессия пользователя с правами администратора
     *                  2) ID в сессии такое же как и в запросе
     * Благодаря этому ообычный пользователь может обновить только себя, а администратор всех
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        int sessionId = (Integer) req.getSession().getAttribute("id");
        int sessionRole = (Integer) req.getSession().getAttribute("role");
        if (id == sessionId || sessionRole == 3) {
            List<Integer> musicTypes = this.stringToArray(Arrays.asList(req.getParameter("musicType").split(",")));
            List<Integer> addresses = this.stringToArray(Arrays.asList(req.getParameter("address").split(",")));
            String name = req.getParameter("name");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            int role = Integer.valueOf(req.getParameter("role"));
            User newUser = new User(name, login, password, role);
            newUser.setAddress(addresses);
            newUser.setMusicTypes(musicTypes);
            newUser.setId(id);
            boolean result = StoreEnum.INSTANCE.updateUser(newUser);
            resp.getWriter().append(new JSONObject().put("success", result).toString()).flush();
        } else {
            resp.getWriter().append(new JSONObject().put("success", false).toString()).flush();
        }
    }

    /**
     * Вспомогательный метод
     * Переводит коллекцию строк в коллекцию целочисленных значений
     * @param strings - коллекция для перевода
     * @return коллекция целочисленных значений
     */
    private List<Integer> stringToArray(List<String> strings) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            result.add(Integer.valueOf(strings.get(i)));
        }
        return result;
    }
}
