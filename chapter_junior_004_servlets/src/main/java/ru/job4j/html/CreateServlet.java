package ru.job4j.html;

import com.google.gson.Gson;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;
import ru.job4j.users.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        Map<Integer, String> allRights = ValidateService.INSTANCE.getRights();
        String rights = new Gson().toJson(allRights);
        System.out.println("rights = " + rights);
        resp.getWriter().append(rights).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        User user = new User(req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                Integer.valueOf(req.getParameter("right")),
                req.getParameter("password"));
        if (ValidateService.INSTANCE.add(user)) {
            System.out.println("create");
            resp.getWriter().append("{\"add\" : \"success\"}").flush();
        } else {
            System.out.println("error");
            resp.getWriter().append("{\"add\" : \"error\"}").flush();
        }
    }
}
