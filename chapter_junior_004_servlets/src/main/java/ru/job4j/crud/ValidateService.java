package ru.job4j.crud;

import java.util.HashMap;

/**
 * Класс, описывающий логику приложения (хотя по моему бесполезный класс)
 * @author Galanov Sergey
 * @since 13.09.2018
 * @version 1.0
 */
public final class ValidateService {

    /**
     * Приватное поле класса
     * Содержит обьект данного класса, который создается при инициализации программы
     */
    private static ValidateService validateService = new ValidateService();

    /**
     * Дефолтный конструктор данного класса
     * Имеет приватный уровень доступа - это необходимо для предотвращения создания обьектов данного класса
     */
    private ValidateService() {

    }

    /**
     * Метод, возращающий обьект данного класса
     * @return - обьект данного класса
     */
    public static ValidateService getInstance() {
        return validateService;
    }

    /**
     * Метод добавления пользователя
     * Вызывает у синглтона метод добавления пользователя
     * @param name - Имя нового пользователя
     * @param login - Пароль нового пользователя
     * @param email - Почта нового пользователя
     * @return false если пользователь с таким именем, логином, паролем уже существует
     */
    public final boolean add(String name, String login, String email) {
        return MemoryStore.getInstance().add(new User(name, login, email));
    }

    /**
     * Метод обновления данных пользователя
     * @param userId - id исходного пользователя
     * @param name - новое имя пользователя
     * @param login - новый логин пользователя
     * @param email - новый пароль пользователя
     * @return true если пользователь с таким id существует и он был обновлен
     */
    public final boolean update(int userId, String name, String login, String email) {
        return MemoryStore.getInstance().update(userId, name, login, email);
    }

    /**
     * Метод удаления пользователя
     * @param userId - id удаляемого пользователя
     * @return true если пользователь с таким id есть и он удален
     */
    public final boolean delete(int userId) {
        return MemoryStore.getInstance().delete(userId);
    }

    /**
     * Метод, возращающий коллекцию всех пользователей
     */
    public final HashMap<Integer, User> findAll() {
        return MemoryStore.getInstance().findAll();
    }

    /**
     * Метод, возращающий пользователя по id
     * @param userId
     */
    public final User findById(int userId) {
        return MemoryStore.getInstance().findById(userId);
    }
}
