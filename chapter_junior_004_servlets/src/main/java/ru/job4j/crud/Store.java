package ru.job4j.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс, описывающий какие методы должен иметь каждый класс, реализующий данный интерфейс
 * По большому счету это методы для работы с коллекцией пользователей
 * @author Galanov Sergey
 * @since 26.09.2018
 * @version 1.2
 */
public interface Store {
    boolean add(User user);
    boolean update(int idOldUser, User user);
    boolean delete(int userId);
    HashMap<Integer, User> findAll();
    User findById(int id);
    boolean checkCorrect(String username, String password);
    Map<Integer, String> getAllRights();
    User checkUser(String userName, String userPassword);
}
