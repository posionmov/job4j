package ru.job4j.crud;

import ru.job4j.users.DbStore;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, описывающий логику приложения (хотя по моему бесполезный класс)
 * @author Galanov Sergey
 * @since 26.09.2018
 * @version 1.2
 */
public enum ValidateService {

    INSTANCE;

    /**
     * Метод добавления пользователя
     * Вызывает у синглтона метод добавления пользователя
     * @param newUser - новый пользователь, которого необходимо добавить в БД
     * @return false если пользователь с таким именем, логином, паролем уже существует
     */
    public final boolean add(User newUser) {
        return DbStore.getInstance().add(newUser);
    }

    /**
     * Метод обновления данных пользователя
     * @return true если пользователь с таким id существует и он был обновлен
     */
    public final boolean update(int idOldUser, User user) {
        return DbStore.getInstance().update(idOldUser, user);
    }

    /**
     * Метод удаления пользователя
     * @param userId - id удаляемого пользователя
     * @return true если пользователь с таким id есть и он удален
     */
    public final boolean delete(int userId) {
        return DbStore.getInstance().delete(userId);
    }

    /**
     * Метод, возращающий коллекцию всех пользователей
     */
    public final HashMap<Integer, User> findAll() {
        return DbStore.getInstance().findAll();
    }

    /**
     * Метод, возращающий пользователя по id
     * @param userId
     */
    public final User findById(int userId) {
        return DbStore.getInstance().findById(userId);
    }

    /**
     * Метод, возращающий мапу всех прав пользователей
     *  ключ - номер из БД конкретного права
     *  значение - название права
     * @return
     */
    public Map<Integer, String> getRights() {
        return DbStore.getInstance().getAllRights();
    }

    /**
     * Метод, проверяющий что пользователь с таким логином и паролем существует
     * @param login - логин
     * @param password - пароль
     * @return обьект класса User
     */
    public User checkUser(String login, String password) {
        return DbStore.getInstance().checkUser(login, password);
    }

    /**
     * Метод, возращающий мапу стран и принадлежащих им городов
     * @return
     */
    public Map<Integer, Map<String, Map<Integer, String>>> getLocation() {
        return DbStore.getInstance().getCountriesAndCities();
    }
}
