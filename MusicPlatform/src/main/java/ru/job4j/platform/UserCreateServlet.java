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

public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        Map<Integer, String> roles = DbStorage.INSTANCE.getAllRoles();
        Map<Integer, String> musicTypes = DbStorage.INSTANCE.getAllTypes();
        Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> allAddresses = DbStorage.INSTANCE.getAllAddresses();
        resp.getWriter().append(new JSONObject().put("roles", roles).put("types", musicTypes).put("addresses", allAddresses).toString()).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int role = Integer.valueOf(req.getParameter("role"));
        String address = req.getParameter("address");
        int musicType = Integer.valueOf(req.getParameter("musicType"));
        System.out.println(req.getParameter("musicType").getClass());
        System.out.println("Тип Музыки - " + musicType);
//        User newUser = new User(name, login, password, role, musicType);
//        boolean result = DbStorage.INSTANCE.addUser(newUser, address);
//        resp.getWriter().append(new JSONObject().put("success", result).toString()).flush();
    }
}
