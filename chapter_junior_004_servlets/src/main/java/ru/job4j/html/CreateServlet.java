package ru.job4j.html;

import org.json.JSONObject;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        Map<Integer, String> allRights = ValidateService.INSTANCE.getRights();
        Map<Integer, Map<String, Map<Integer, String>>> allLocations = ValidateService.INSTANCE.getLocation();
        JSONObject result = new JSONObject().put("rights", allRights).put("locations", allLocations);
        String json = result.toString();
        System.out.println(json);
        resp.getWriter().append(json).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                Integer.valueOf(req.getParameter("right")),
                req.getParameter("password"),
                Integer.valueOf(req.getParameter("city")),
                Integer.valueOf(req.getParameter("country")));
        if (ValidateService.INSTANCE.add(user)) {
            resp.getWriter().append(new JSONObject().put("add", "success").toString()).flush();
        } else {
            resp.getWriter().append(new JSONObject().put("add", "error").toString()).flush();
        }
    }
}
