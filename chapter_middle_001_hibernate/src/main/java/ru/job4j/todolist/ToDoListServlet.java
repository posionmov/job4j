package ru.job4j.todolist;

import org.json.JSONObject;
import ru.job4j.store.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Сервлет получения и обновления информации из БД для программы todoList
 * @author Galanov Sergey
 * @since 22.10.2018
 * @version 1.3
 */
public class ToDoListServlet extends HttpServlet {

    /**
     * Метод отправи данных о всех записях в БД
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ListModel> list = ValidateService.INSTANCE.getAllObject();
        resp.getWriter().append(new JSONObject().put("items", list).toString()).flush();
    }

    /**
     * Метод для внесения изменений в данные на сервере: закрытие задачи
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("operation").equals("change")) {
            ListModel model = new ListModel();
            model.setId(Integer.valueOf(req.getParameter("id")));
            model.setDone(Boolean.valueOf(req.getParameter("done")));
            ValidateService.INSTANCE.updateObject(model);
        } else if (req.getParameter("operation").equals("add")) {
            ListModel listModel = new ListModel();
            listModel.setDesc(req.getParameter("description"));
            listModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
            listModel.setDone(false);
            ValidateService.INSTANCE.addNewObject(listModel);
        }
    }
}
