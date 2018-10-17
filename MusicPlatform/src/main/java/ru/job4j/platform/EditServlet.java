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

public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Integer> musicTypes = this.stringToArray(Arrays.asList(req.getParameter("musicType").split(",")));
        List<Integer> addresses = this.stringToArray(Arrays.asList(req.getParameter("address").split(",")));
        int id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int role = Integer.valueOf(req.getParameter("role"));
        User newUser = new User(name, login, password, role);
        newUser.setAddress(addresses);
        newUser.setMusicTypes(musicTypes);
        newUser.setId(id);
        boolean result = DbStorage.INSTANCE.updateUser(newUser);
        resp.getWriter().append(new JSONObject().put("success", result).toString()).flush();
    }

    private List<Integer> stringToArray(List<String> strings) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            result.add(Integer.valueOf(strings.get(i)));
        }
        return result;
    }
}
