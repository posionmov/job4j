package ru.job4j.servlets;

import org.json.JSONObject;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.store.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет регистрации пользователя
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Метод, отрабатывающий при запросе метода POST
     * Сохраняет пользователя в БД
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setRole(new Role(2));
        if (ValidateService.INSTANCE.addUser(user).getId() != 0) {
            resp.getWriter().append(new JSONObject().put("add", "success").toString()).flush();
        } else {
            resp.getWriter().append(new JSONObject().put("add", "fail").toString()).flush();
        }
    }
}
