package ru.job4j.html;

import com.google.gson.Gson;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UsersListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        HashMap<Integer, User> allUsers = ValidateService.INSTANCE.findAll();
        Map<Integer, String> allRights = ValidateService.INSTANCE.getRights();
        int id = Integer.valueOf(new Gson().toJson(req.getSession().getAttribute("id")));
        int right = Integer.valueOf(new Gson().toJson(req.getSession().getAttribute("right")));
        Map<Integer, Map<String, Map<Integer, String>>> allLocations = ValidateService.INSTANCE.getLocation();

        String users = new Gson().toJson(allUsers);
        String rights = new Gson().toJson(allRights);
        String locations = new Gson().toJson(allLocations);

        resp.getWriter().append("{\"id\" : " + id + ", \"right\" : \"" + right + "\", \"users\" : " + users + ", \"rights\" : " + rights + ", \"locations\" : " + locations + "}").flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout").equals("true")) {
            req.getSession().setAttribute("login", null);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = req.getIntHeader("id");
        if (ValidateService.INSTANCE.delete(id)) {
            resp.getWriter().append("{\"delete\" : \"success\"}").flush();
        } else {
            resp.getWriter().append("{\"delete\" : \"fail\"}").flush();
        }

    }
}
