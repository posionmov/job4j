package ru.job4j.storage;

import ru.job4j.model.User;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс, содержащий в себе методы, необходимые для работы с БД
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public interface Store {

    /**
     * Метод добавления пользователя
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * Метод получения всех прав доступа
     * @return
     */
    Map<Integer, String> getAllRoles();

    /**
     * Метод получения всех типов музыки
     * @return
     */
    Map<Integer, String> getAllTypes();

    /**
     * Метод получения всех адресов
     * @return
     */
    Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> getAllAddresses();

    /**
     * Метод получения юзеров
     * @param type - типа запроса
     * @param address - коллекция адресов
     * @param role - искомое право доступа
     * @param types - коллекция типов музыки
     * @param stringForSearch - строка для поиска
     * @return
     */
    List<User> getUsers(String type, List<Integer> address, int role, List<Integer> types, String stringForSearch);

    /**
     * Метод получения пользователя по id
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * Метод обновления пользователя
     * @param newUser
     * @return
     */
    boolean updateUser(User newUser);

    /**
     * Метод удаления пользователя
     * @param userId
     * @return
     */
    boolean deleteUser(int userId);

    /**
     * Метод проверки наличия пользователя по логину и паролю (необходимо для авторизации)
     * @param login
     * @param password
     * @return
     */
    User checkUser(String login, String password);


}
