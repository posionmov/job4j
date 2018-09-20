package ru.job4j.users;

import com.sun.javafx.fxml.builder.URLBuilder;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;

/**
 * Класс, наследующий класс HttpServlet
 * Выступает в роли сервлета
 * Основная задача данного класса - Открывать таблицу со всеми пользователями
 * Около каждого пользователя должны быть кнопки: - редактировать
 *                                                - удалить
 * @author Galanov Sergey
 * @since 14.09.2018
 * @version 1.0
 */
public class UsersServlet extends HttpServlet {

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается когда от клиента приходит запрос методом Get
     * Данный метод отображает таблицу всех пользователей
     * Около каждого пользователя (строки) должны быть 2 кнопки (редактировать и удалить)
     * @param req - запрос от пользователя
     * @param resp - ответ пользователю
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ValidateService validateService = ValidateService.INSTANCE;
        resp.setCharacterEncoding("UTF-8");
        StringBuilder stringBuilder = new StringBuilder("<table border=\"1\" cellpadding=\"6\">");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td colspan=\"6\" align=\"center\">" +
                                "<form action=" + req.getContextPath() + "/create method=\"get\">" +
                                "<input type=\"submit\" value=\"Созать пользователя\"/></form></td>");
        stringBuilder.append("</tr>");
        Collection<User> usersInStore = validateService.findAll().values();
        if (usersInStore.size() > 0) {

            stringBuilder.append("<tr>");
            stringBuilder.append("<td>" + "Id пользователя" + "</td>");
            stringBuilder.append("<td>" + "Имя пользователя" + "</td>");
            stringBuilder.append("<td>" + "Логин пользователя" + "</td>");
            stringBuilder.append("<td>" + "Почта пользователя" + "</td>");
            stringBuilder.append("<td>" + "Дата создания пользователя"+ "</td>");
            stringBuilder.append("<td>" + "Методы над пользователем"+ "</td>");

            for (User user : validateService.findAll().values()) {
                stringBuilder.append("<tr>");
                stringBuilder.append("<td>" + user.getId() + "</td>");
                stringBuilder.append("<td>" + user.getName() + "</td>");
                stringBuilder.append("<td>" + user.getLogin() + "</td>");
                stringBuilder.append("<td>" + user.getEmail() + "</td>");
                stringBuilder.append("<td>" + user.getCreateDate() + "</td>");

                stringBuilder.append("<td><form action=" + req.getContextPath() + "/edit method=\"get\">" +
                        "<input type=\"hidden\" name=\"id\" value=" + user.getId() + ">" +
                        "<input type=\"submit\" value=\"Редактировать пользователя\"/></form>");

                stringBuilder.append("<form action=" + req.getContextPath() + "/list method=\"post\">" +
                        "<input type=\"hidden\" name=\"id\" value=" + user.getId() + ">" +
                        "<input type=\"hidden\" name=\"type\" value=\"delete\">" +
                        "<input type=\"submit\" value=\"Удалить пользователя\"/></form></td>");

                stringBuilder.append("</tr>");
            }
        }

        stringBuilder.append("</table>");
        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Список всех пользователей</title>\n" +
                "</head>\n" +
                "<body>\n" +
                stringBuilder + "\n" +
                "</body>\n" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("type").equals("delete")) {
            this.doDelete(req, resp);
        }
    }

    //todo - DELETE - метод HTTP
    //todo - В HTML его нет (есть только get и post)
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ValidateService validateService = ValidateService.INSTANCE;
        int id = Integer.valueOf(req.getParameter("id"));
        System.out.println("id для удаления - " + id);
        StringBuilder stringBuilder = new StringBuilder("<table border=\"1\" cellpadding=\"6\">");
        validateService.delete(id);
        stringBuilder.append("<tr><td align=\"center\">Пользователь с id:" + id + " удален." + "</td></tr>");
        stringBuilder.append("<tr><td align=\"center\"><form action=" + req.getContextPath() + "/list method=\"get\">");
        stringBuilder.append("<input type=\"submit\" value=\"Вернуться назад\"/></form></td></tr>");
        stringBuilder.append("</table>");
        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Список всех пользователей</title>\n" +
                "</head>\n" +
                "<body>\n" +
                stringBuilder + "\n" +
                "</body>\n" +
                "</html>");
        writer.flush();
    }
}
