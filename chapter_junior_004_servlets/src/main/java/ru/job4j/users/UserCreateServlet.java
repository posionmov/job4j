package ru.job4j.users;

import com.google.gson.Gson;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Класс-сервлет, отвечающий за создание пользователя
 *
 * @author Galanov Sergey
 * @version 1.1
 * @since 26.09.2018
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается если от пользователя приходит запрос Get
     * Устанавливает в запросе аттрибут Operation со значением show
     * Устанавливает в запросе аттрибут rights со значением мапы всех прав пользователей
     * После этого отправляет запрос и ответ в jsp страницу, где выводится ответ пользователю в зависимости от значения
     * аттрибута Operation
     *
     * @param req  - запрос
     * @param resp - ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateOut = ValidateService.INSTANCE;

        Map<Integer, String> bdRights = validateOut.getRights();
        Gson gsonForRights = new Gson();
        String rights = gsonForRights.toJson(bdRights);
        System.out.println(rights);
        req.setAttribute("rgh", rights);

        System.out.println("get");

        req.setAttribute("Operation", "show");
        req.setAttribute("rights", validateOut.getRights());
        req.getRequestDispatcher("WEB-INF/views/UsersCreate.jsp").forward(req, resp);
    }

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается если от пользователя приходит запрос Post
     * Данный запрос по дефолту получается от пользователя при заполнении формы в jsp страницы в данном сервлете
     * 1) Создает пользователя на основе данныз из формы
     * 2) Задает в запросе аттрибут Operation со значением add, что позволяет вывести в jsp странице
     * 3) Если пользователь успешно создан, то задает в запросе аттрибут adding со значением success
     * иначе со значением fail
     * 4) После этого отправляет запрос и ответ в jsp страницу
     *
     * @param req  - запрос
     * @param resp - ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        req.setAttribute("Operation", "add");
        ValidateService validateOut = ValidateService.INSTANCE;
        User user = new User(req.getParameter("name"),
                            req.getParameter("login"),
                            req.getParameter("email"),
                            Integer.valueOf(req.getParameter("right")),
                            req.getParameter("password"),
                            Integer.valueOf(req.getParameter(req.getParameter("city"))),
                            req.getIntHeader("country"));



        if (validateOut.add(user)) {
            req.setAttribute("adding", "success");
            req.setAttribute("id", user.getId());
        } else {
            System.out.println("cand add user");
            req.setAttribute("Adding", "fail");
            req.setAttribute("id", user.getId());
        }
        req.setAttribute("adding", "success");
        req.getRequestDispatcher("WEB-INF/views/UsersCreate.jsp").forward(req, resp);
    }
}
