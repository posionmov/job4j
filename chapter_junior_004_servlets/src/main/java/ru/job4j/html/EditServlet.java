package ru.job4j.html;

import com.google.gson.Gson;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;
import ru.job4j.users.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        int sessionRight = (int) req.getSession().getAttribute("right");
        User  user = ValidateService.INSTANCE.findById(id);
        Map<Integer, String> allRights = ValidateService.INSTANCE.getRights();

        String rights = new Gson().toJson(allRights);
        String curUser = new Gson().toJson(user);

        resp.getWriter().append("{\"user\" : " + curUser + ", \"curRight\" : " + sessionRight + ",\"rights\" : " + rights  + "}").flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateOut = ValidateService.INSTANCE;
        HttpSession session = req.getSession();
        Map<Integer, String> rights = validateOut.getRights();
        int id = Integer.valueOf(req.getParameter("id"));
        User user = validateOut.findById(id);
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        if (req.getSession().getAttribute("right").equals("1")) {
            user.setRight(Integer.valueOf(req.getParameter("right")));
            if (!session.getAttribute("right").equals(rights.get(Integer.valueOf(req.getParameter("right"))))) {
                session.setAttribute("right", rights.get(Integer.valueOf(req.getParameter("right"))));
            }
        }
        validateOut.update(id, user);
        resp.getWriter().append("{\"update\" : \"success\"}").flush();
    }
}
