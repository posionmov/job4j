package ru.job4j.servlets;

import org.json.JSONObject;
import ru.job4j.models.User;
import ru.job4j.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Boolean.valueOf(req.getParameter("signOut"))) {
            req.getSession().setAttribute("logged", null);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user = DbStore.INSTANCE.checkUser(user);
        if (user != null) {
            req.getSession().setAttribute("logged", user);
            resp.getWriter().append(new JSONObject().put("auth", true).toString()).flush();
        } else {
            resp.getWriter().append(new JSONObject().put("auth", false).toString()).flush();
        }
    }
}
