package ru.job4j.servlets;

import org.json.JSONObject;
import ru.job4j.models.User;
import ru.job4j.store.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет авторизации
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class AuthorizationServlet extends HttpServlet {

    /**
     * Метод, отрабатывающий при запросе метода GET
     * Устанавливает в сессию новое значение по ключу logged
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Boolean.valueOf(req.getParameter("signOut"))) {
            req.getSession().setAttribute("logged", null);
        }
    }

    /**
     * Метод, отрабатывающий при запросе метода POST
     * Проверяет существование юзера по полученному логину и паролю и устанавливает соответствующие значения
     *          в сессию и в ответ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user = ValidateService.INSTANCE.checkUser(user);
        if (user != null) {
            req.getSession().setAttribute("logged", user);
            resp.getWriter().append(new JSONObject().put("auth", true).toString()).flush();
        } else {
            resp.getWriter().append(new JSONObject().put("auth", false).toString()).flush();
        }
    }
}
