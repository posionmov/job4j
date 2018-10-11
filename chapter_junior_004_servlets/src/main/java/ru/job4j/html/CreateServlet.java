package ru.job4j.html;

import com.google.gson.Gson;
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
        String rights = new Gson().toJson(allRights);
        String locations = new Gson().toJson(allLocations);
        resp.getWriter().append("{\"rights\" : " + rights + ", \"locations\" : " + locations + "}").flush();
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
            resp.getWriter().append("{\"add\" : \"success\"}").flush();
        } else {
            resp.getWriter().append("{\"add\" : \"error\"}").flush();
        }
    }
}
