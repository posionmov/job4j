package ru.job4j.users;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Сервлет для обработки json
 * @author Galanov Sergey
 * @version 1.0
 * @since 04.10.2018
 */
public class JsonServlet extends HttpServlet {

    /**
     * Поля класса
     * Содержат в себе потокобезопасную мапу, которая будет хранить данные о пользователе
     * Текущий индекс, использующийся в качестве ключа в потокобезопасной мапе
     */
    private ConcurrentHashMap<Integer, String> values = new ConcurrentHashMap<>();
    private volatile int curIndex = 1;


    /**
     * Метод, срабатывающий при инициализации сервлета
     * Задает начальную записать в потокобезопасной мапе
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        values.put(this.curIndex, "{\"name\" : \"name1\", \"surname\" : \"surname1\", \"gender\" : \"gender1\", \"description\" : \"description1\"}");
    }

    /**
     * Метод, печатающий на странице json, исходя из наполнения потокобезопасной мапы
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[");
        int curIndex = 1;
        for (String value : values.values()) {
            writer.append(value);
            if (curIndex != this.curIndex) {
                writer.append(", ");
            }
            curIndex++;
        }
        writer.append("]");
        writer.flush();
    }

    /**
     * Метод, получающий из ajax данные и записывающий их в потокобезопасную мапу
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String gender = req.getParameter("gender");
        String description = req.getParameter("description");
        String stringToPut = String.format("{\"name\" : \"" + name + "\", \"surname\" : \"" + surname + "\", \"gender\" : \"" + gender + "\", \"description\" : \"" + description + "\"}");
        this.values.put(++curIndex, stringToPut);
    }
}
