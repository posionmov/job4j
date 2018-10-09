package ru.job4j.users;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Севрлет авторизации
 *
 * @author Galanov Sergey
 * @version 1.0
 * @since 26.09.2018
 */
public class UserAuthorization extends HttpServlet {

    /**
     * Метод, работающий когда от пользователя приходит запрос типа GET
     * Делает редирект на страницу списка всех пользователей если аттрибут сессии "login" не равен нулю
     * В остальных случаях посылает на странцу авторизации
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("login") != null) {
//            req.getRequestDispatcher("WEB-INF/views/UsersList.jsp").forward(req, resp); // Не может давать редирект через диспатчер в файлы, лежащие в папке WEB-INF!!!
            resp.sendRedirect(String.format("%s/list", req.getContextPath())); // Это почему то работает
        }  else  {
            JsonRedirect jsonRedorect = new JsonRedirect(String.format("%s/create", req.getContextPath()));
            Gson gsonForRedirect = new Gson();
            String redirect = gsonForRedirect.toJson(jsonRedorect);
            System.out.println("FROM GET!!!!!!!");
            req.setAttribute("redirect", redirect);
            req.getRequestDispatcher("WEB-INF/views/UserAuth.jsp").forward(req, resp);
        }
    }

    /**
     * Метод, работающий когда от пользователя приходит запрос типа POST
     * 1) Если в параметре "operation" указано значение "signIn", то записывает значения полей login и password в локальные переменные
     * - Затем ищут пользователя по этим данным (логин и пароль). Кроме того получает мапу всех значений прав пользовтелей.
     * - Если пользователь по предоставленным логину и паролю существует, то задает в сессии аттрибуты "login" и "right" с соответствующими
     * значениями пользователя и сохраняет в ответ редирект на адрес страницы с таблицей всех пльзователей
     * - Если такого пользователя нет, то сохранет в ответ пользователю редирект на саму себя
     * 2) Если в параметре "operation" указано значение "signUp", то записывает в ответ пользователю редирект на страницу создания аккаунта
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("operation").equals("signIn")) {
            ValidateService validateOut = ValidateService.INSTANCE;
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            User user = validateOut.checkUser(login, password);
            Map<Integer, String> rights = validateOut.getRights();
            if (user != null) {
                System.out.println("user is not null");
                HttpSession session = req.getSession();
                session.setAttribute("login", user.getLogin());
                session.setAttribute("right", rights.get(user.getRight()));
                this.doGet(req, resp);
            } else {
                resp.sendRedirect(String.format("%s/signIn", req.getContextPath()));
            }
        }
    }

    private class JsonRedirect {
        String redirect;


        public JsonRedirect(String redirect) {
            this.redirect = redirect;
        }

        public void setRedirect(String redirect) {
            this.redirect = redirect;
        }

        public String getRedirect() {
            return redirect;
        }
    }
}
