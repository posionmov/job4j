package ru.job4j.todolist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.json.JSONObject;

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
 * @since 20.10.2018
 * @version 1.1
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
        resp.setCharacterEncoding("utf-8");
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        List<ListModel> list = session.createQuery("from ListModel").list();
        resp.getWriter().append(new JSONObject().put("items", list).toString()).flush();
        session.getTransaction().commit();
        session.close();
        factory.close();
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
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        if (req.getParameter("operation").equals("change")) {
            Query query = session.createQuery("update ListModel set done = :done where id = :id");
            query.setParameter("done", Boolean.valueOf(req.getParameter("done")));
            query.setParameter("id", Integer.valueOf(req.getParameter("id")));
            query.executeUpdate();
        } else if (req.getParameter("operation").equals("add")) {
            ListModel listModel = new ListModel();
            listModel.setDesc(req.getParameter("description"));
            listModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
            listModel.setDone(false);
            session.save(listModel);
        }
        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
