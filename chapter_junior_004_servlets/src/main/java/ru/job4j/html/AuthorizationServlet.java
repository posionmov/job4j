package ru.job4j.html;

import com.google.gson.Gson;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User checkedUser = ValidateService.INSTANCE.checkUser(login, password);
        if (checkedUser != null) {
            req.getSession().setAttribute("id", checkedUser.getId());
            req.getSession().setAttribute("right", checkedUser.getRight());
            req.getSession().setAttribute("login", true);
            String jsonUser = new Gson().toJson(checkedUser);
            resp.getWriter().append(jsonUser).flush();
        } else {
            resp.getWriter().append("{\"login\" : \"false\" }");
        }
    }
}
