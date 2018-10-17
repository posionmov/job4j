package ru.job4j.platform;

import org.json.JSONObject;
import ru.job4j.model.User;
import ru.job4j.storage.StoreEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет, отвечающий за авторизацию пользователя
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public class LoginServlet extends HttpServlet {
    /**
     * Метод, отрабатывающий когда на адрес сервлета приходит запрос типа GET.
     * Проверяет наличие пользователя в БД по полученному с HTML страницы логину и возращает ответ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User isUserExist = StoreEnum.INSTANCE.checkUser(req.getParameter("login"), req.getParameter("password"));
        if (isUserExist != null) {
            req.getSession().setAttribute("login", true);
            req.getSession().setAttribute("id", isUserExist.getId());
            req.getSession().setAttribute("role", isUserExist.getRole());
            resp.getWriter().append(new JSONObject().put("success", true).toString()).flush();
        } else {
            resp.getWriter().append(new JSONObject().put("success", false).toString()).flush();
        }
    }
}
