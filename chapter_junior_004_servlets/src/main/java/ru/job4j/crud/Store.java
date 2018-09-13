package ru.job4j.crud;

import java.util.HashMap;

/**
 * Интерфейс, описывающий какие методы должен иметь каждый класс, реализующий данный интерфейс
 * По большому счету это методы для работы с коллекцией пользователей
 * @author Galanov Sergey
 * @since 13.09.2018
 * @version 1.0
 */
public interface Store {
    boolean add(User user);
    boolean update(int userId, String name, String login, String email);
    boolean delete(int userId);
    HashMap<Integer, User> findAll();
    User findById(int id);
}
