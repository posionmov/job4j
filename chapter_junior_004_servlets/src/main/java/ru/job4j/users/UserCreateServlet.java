package ru.job4j.users;

import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        resp.setCharacterEncoding("UTF-8");
        StringBuilder stringBuilder = new StringBuilder("<table border=\"1\" cellpadding=\"6\">");

        stringBuilder.append("<form method=\"post\"");
        stringBuilder.append("<tr><td align=\"center\">Создание пользователя</td></tr>");
        stringBuilder.append("<tr><td align=\"right\">Имя: <input type=\"text\" name=\"name\"></td></tr>");
        stringBuilder.append("<tr><td align=\"right\">Логин: <input type=\"text\" name=\"login\"></td></tr>");
        stringBuilder.append("<tr><td align=\"right\">Пароль: <input type=\"text\" name=\"email\"></td></tr>");
        stringBuilder.append("<tr><td align=\"center\"><input type=\"submit\" value=\"Созать пользователя\"></td></tr>");
        stringBuilder.append("</form>");
        stringBuilder.append("</table>");

        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Создание пользователя</title>\n" +
                "</head>\n" +
                "<body>\n" +
                stringBuilder + "\n" +
                "</body>\n" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateService = ValidateService.INSTANCE;
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        req.setCharacterEncoding("UTF-8");

        StringBuilder stringBuilder = new StringBuilder("<table border=\"1\" cellpadding=\"6\">");
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        if (validateService.add(user)) {
            stringBuilder.append("<tr><td>Пользователь " + req.getParameter("name") + " Был создан!</td></tr>");
        } else {
            stringBuilder.append("<tr><td align=\"center\">Такой пользователь уже существует</td></tr>");
        }
        stringBuilder.append("<tr><td align=\"center\"><form action=" + req.getContextPath() + "/list method=\"get\">");
        stringBuilder.append("<input type=\"submit\" value=\"Вернуться к списку пользователей\"/></form></td></tr>");
        stringBuilder.append("</form>");

        writer.append("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Создание пользователя</title>\n" +
                        "</head>\n" +
                        "<body>\n");

        writer.append(stringBuilder + "\n" +
                "</body>\n" +
                "</html>");

        writer.flush();
    }
}
