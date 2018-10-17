package ru.job4j.platform;

import org.json.JSONObject;
import ru.job4j.model.User;
import ru.job4j.storage.DbStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        List<User> users = DbStorage.INSTANCE.getUsers("all", new ArrayList<>(), 0, new ArrayList<>(), "");
        Map<Integer,String> roles = DbStorage.INSTANCE.getAllRoles();
        Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> addresses = DbStorage.INSTANCE.getAllAddresses();
        resp.getWriter().append(new JSONObject().put("users", users).put("roles", roles).put("addresses", addresses).toString()).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        boolean isDeleted = DbStorage.INSTANCE.deleteUser(req.getIntHeader("id"));
        resp.getWriter().append(new JSONObject().put("deleted", isDeleted).toString()).flush();
    }
}
