package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.todolist.ListModel;

import java.util.List;
import java.util.function.Function;

/**
 * Класс для работы с бд
 * @author Galanov Sergey
 * @since 22.10.2018
 * @version 1.0
 */
public class DbStore implements Store {

    /**
     * Поля класса
     * Содержат статические переменные:
     *      - обьекта данного класса
     *      - фабрики
     * На сколько я понял при создании подключения к фабрике подключение автоматически обрывается как только
     *  будет выход за пределы таймера (после проверки)
     */
    public static final DbStore INSTANCE = new DbStore();
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    /**
     * Переопределенный метод получения всех обьектов
     * @return коллекция обьектов
     */
    @Override
    public List<ListModel> getAllObject() {
        return this.tx(session -> session.createQuery("from ListModel").list());
    }

    /**
     * Переопределенный метод добавления обьекта в БД
     * @param model - обьект класса ListModel, которог необходимо добавить в БД
     */
    @Override
    public void addNewObject(ListModel model) {
        this.tx(session -> session.save(model));
    }

    /**
     * Метод обновления обьекта в БД (установка ему поля done)
     * @param model - обьект класса ListModel, который необходимо обновить в БД
     */
    @Override
    public void updateObject(ListModel model) {
        this.tx(session -> { session.saveOrUpdate(model); return null; });
    }


    /**
     * Метод, принимающий в себя лямбда выражение и производящий её выполненеи
     * @param command
     * @param <T>
     * @return
     */
    private <T> T tx(final Function<Session, T> command) {
        Session session = FACTORY.openSession();
        session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

}
