package ru.job4j.users;

import com.google.gson.Gson;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class ExampleServlet extends HttpServlet {

    private static String method = "get";
    private static String res = "{\"method\" : \"" + method + "\"}";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet get");

        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("{\"method\" : \"get\"}");
        writer.flush();

//        req.getRequestDispatcher("example.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<Integer, User> getUsers = ValidateService.INSTANCE.findAll();
        Gson users = new Gson();
        String usrs = users.toJson(getUsers);
        System.out.println(usrs);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(usrs);
        writer.flush();
//        req.getRequestDispatcher("WEB-INF/views/example.html").forward(req, resp);
    }
}
