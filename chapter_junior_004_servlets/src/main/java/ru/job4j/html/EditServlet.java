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

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        int id = Integer.valueOf(req.getParameter("id"));
        int sessionRight = (int) req.getSession().getAttribute("right");
        User user = ValidateService.INSTANCE.findById(id);
        Map<Integer, String> allRights = ValidateService.INSTANCE.getRights();
        Map<Integer, Map<String, Map<Integer, String>>> allLocations = ValidateService.INSTANCE.getLocation();
        JSONObject result = new JSONObject().put("user", new JSONObject(user)).put("locations", allLocations).put("curRight", sessionRight).put("rights", allRights);
        resp.getWriter().append(result.toString()).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateOut = ValidateService.INSTANCE;
        int id = Integer.valueOf(req.getParameter("id"));
        User user = validateOut.findById(id);
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setCity(Integer.valueOf(req.getParameter("city")));
        user.setCountry(Integer.valueOf(req.getParameter("country")));
        if (req.getSession().getAttribute("right").equals(1)) {
            user.setRight(Integer.valueOf(req.getParameter("right")));
        }
        validateOut.update(id, user);
        resp.getWriter().append(new JSONObject().put("update", "success").toString()).flush();
    }
}
