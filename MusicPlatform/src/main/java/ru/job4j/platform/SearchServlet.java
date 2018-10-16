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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        if (req.getParameter("type").equals("address")) {
            System.out.println("address");
            List<Integer> address = new ArrayList<>();
            address.add(Integer.valueOf(req.getParameter("country")));
            address.add(Integer.valueOf(req.getParameter("city")));
            address.add(Integer.valueOf(req.getParameter("street")));
            List<User> users = DbStorage.INSTANCE.getUsers("address", address, 0, new ArrayList<>(), "");
            resp.getWriter().append(new JSONObject().put("users", users).toString()).flush();
        } else if (req.getParameter("type").equals("role")) {
            System.out.println("roles");
            List<User> users = DbStorage.INSTANCE.getUsers("role", new ArrayList<>(), Integer.valueOf(req.getParameter("role")), new ArrayList<>(), "");
            resp.getWriter().append(new JSONObject().put("users", users).toString()).flush();
        } else if (req.getParameter("type").equals("types")) {
            List<Integer> musicTypes = this.stringToArray(Arrays.asList(req.getParameter("music").split(",")));
            List<User> users = DbStorage.INSTANCE.getUsers("types", new ArrayList<>(), 0, musicTypes, "");
            resp.getWriter().append(new JSONObject().put("users", users).toString()).flush();
        } else if (req.getParameter("type").equals("string")) {
            List<User> users = DbStorage.INSTANCE.getUsers("string", new ArrayList<>(), 0, new ArrayList<>(), req.getParameter("string"));
            resp.getWriter().append(new JSONObject().put("users", users).toString()).flush();
        }
    }

    private List<Integer> stringToArray(List<String> strings) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            result.add(Integer.valueOf(strings.get(i)));
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
