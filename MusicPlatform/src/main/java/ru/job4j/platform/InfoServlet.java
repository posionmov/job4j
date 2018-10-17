package ru.job4j.platform;

import org.json.JSONObject;
import ru.job4j.model.User;
import ru.job4j.storage.DbStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        int id = Integer.valueOf(req.getParameter("id"));
        Map<Integer, String> musicTypes = DbStorage.INSTANCE.getAllTypes();
        User user = DbStorage.INSTANCE.getUserById(id);
        Map<Integer,String> roles = DbStorage.INSTANCE.getAllRoles();
        Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> addresses = DbStorage.INSTANCE.getAllAddresses();
        resp.getWriter().append(new JSONObject().put("user", new JSONObject(user)).put("roles", roles).put("addresses", addresses).put("types", musicTypes).toString()).flush();
    }
}
