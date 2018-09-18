package ru.job4j.crud;

import java.util.HashMap;

/**
 * Интерфейс, описывающий какие методы должен иметь каждый класс, реализующий данный интерфейс
 * По большому счету это методы для работы с коллекцией пользователей
 * @author Galanov Sergey
 * @since 18.09.2018
 * @version 1.1
 */
public interface Store {
    boolean add(User user);
    boolean update(int idOldUser, User user);
    boolean delete(int userId);
    HashMap<Integer, User> findAll();
    User findById(int id);
}
