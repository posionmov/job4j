package ru.job4j.users;

import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateService = ValidateService.INSTANCE;
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        req.setCharacterEncoding("UTF-8");
        User user = (User) validateService.findById(Integer.parseInt(req.getParameter("id")));
        StringBuilder stringBuilder = new StringBuilder("<table border=\"1\" cellpadding=\"6\">");
        stringBuilder.append("<form action=" + req.getContextPath() + "/edit method=\"post\"");
        stringBuilder.append("<tr><td align=\"right\">Редактирование пользователя (id =" + user.getId() + "<input type=\"hidden\" name=\"id\" value=" + user.getId() + "></td></tr>"); // Скрытое поле!!!
        stringBuilder.append("<tr><td align=\"right\">Имя: <input type=\"text\" name=\"name\" value=" + user.getName() +  "></td></tr>");
        stringBuilder.append("<tr><td align=\"right\">Логин: <input type=\"text\" name=\"login\" value=" + user.getLogin() + "></td></tr>");
        stringBuilder.append("<tr><td align=\"right\">Почта: <input type=\"text\" name=\"email\" value=" + user.getEmail() + "></td></tr>");
        stringBuilder.append("<tr><td align=\"center\"><input type=\"submit\" value=\"Редактировать пользователя\"></td></tr>");
        stringBuilder.append("</form>");
        stringBuilder.append("</table>");

        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Редактирование пользователя с id = " + req.getAttribute("id") + "</title>\n" +
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
        int id = Integer.valueOf(req.getParameter("id"));
        User user = validateService.findById(id);
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        validateService.update(id, user);

        StringBuilder stringBuilder = new StringBuilder("<table border=\"1\" cellpadding=\"6\">");
        stringBuilder.append("<tr><td align=\"center\">Пользователь с id: " + user.getId() + " изменен.</td></tr>");
        stringBuilder.append("<tr><td align=\"center\"><form action=" + req.getContextPath() + "/list method=\"get\">");
        stringBuilder.append("<input type=\"submit\" value=\"Вернуться к списку пользователей\"/></form></td></tr>");
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
}

