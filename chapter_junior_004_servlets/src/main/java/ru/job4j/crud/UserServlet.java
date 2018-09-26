package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Класс-сервлет
 * @author Galanov Sergey
 * @since 26.09.2018
 * @version 1.2
 */
public class UserServlet extends HttpServlet {

    /**
     * Переопределенный метод класса HttpServlet
     * Вызывается тогда, когда клиент посылает запрос типа Get на сервер
     *
     * @param req - запрос от пользователя, никакие поля не обрабатываются
     * @param resp - ответ пользователю, содержит в себе набор строк (текстовый поток)
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=utf-8");
        ValidateService validateOut = ValidateService.INSTANCE;
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        for (User user : validateOut.findAll().values()) {
            writer.append("id = " + user.getId() + " ,name = " + user.getName()
                    + " ,login = " + user.getLogin() + " ,email = " + user.getEmail()
                    + " creating date = " + user.getCreateDate() + System.lineSeparator());
        }
        writer.flush();
    }

    /**
     * Метод, вызывающийся при отправке от клиента запроса типа DELETE
     * Удаляет элемент из коллекции 
     * @param req - запрос
     * @param resp - ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateOut = ValidateService.INSTANCE;
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        req.setCharacterEncoding("UTF-8");
        if (validateOut.delete(Integer.valueOf(req.getParameter("id")))) {
            System.out.println("Пользователь удален");
            writer.append("User " + req.getParameter("id") + " was deleted!");
        } else {
            writer.append("Don't find this user");
        }
        writer.flush();
    }

    /**
     * Метод обработки получаемых данных
     * Работает с методами add, update, delete и getById класса ValidateService.java
     * Производит добавление, изменение, удалени и поиск пользователя
     * @param req - запрос от пользователя, обрабатываются значения, полученные
     *                              от пользователя (но не явно, а в теле запроса)
     * @param resp - ответ пользователю, содержит в себе набор строк (текстовый поток)
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateService = ValidateService.INSTANCE;
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action.equals("add")) {
            User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), Integer.valueOf(req.getParameter("right")), req.getParameter("password"));
            if (validateService.add(user)) {
                System.out.println("Пользователь создан");
                writer.append("User " + req.getParameter("name") + " was created!");
            } else {
                writer.append("User already exist").flush();
            }
        } else if (action.equals("update")) {
            int oldId = Integer.valueOf(req.getParameter("id"));
            String newName = req.getParameter("name");
            String newLogin = req.getParameter("login");
            String newEmail = req.getParameter("email");
            int newRight = Integer.valueOf(req.getParameter("right"));
            String newPassword = req.getParameter("password");
            User newUser = new User(newName, newLogin, newEmail, newRight, newPassword);
            if (validateService.update(oldId, newUser)) {
                System.out.println("Пользователь обновлен");
                writer.append("User " + oldId + " was updated!");
            } else {
                writer.append("Don't find this user");
            }
        } else if (action.equals("getById")) {
            User user = validateService.findById(Integer.valueOf(req.getParameter("id")));
            if (!(user == null)) {
                writer.append(user.getId() + " " + user.getName() + " "
                        + user.getLogin() + " " + user.getEmail() + " "
                        + user.getCreateDate() + System.lineSeparator());
            } else {
                writer.append("Cant find this user");
            }
        }
        writer.flush();
    }
}
