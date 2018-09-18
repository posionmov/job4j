package ru.job4j.crud;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import java.util.HashMap;

/**
 * Класс, описывающий логику приложения (хотя по моему бесполезный класс)
 * @author Galanov Sergey
 * @since 18.09.2018
 * @version 1.1
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
        return MemoryStore.INSTANCE.add(newUser);
    }

    /**
     * Метод обновления данных пользователя
     * @return true если пользователь с таким id существует и он был обновлен
     */
    public final boolean update(int idOldUser, User user) {
        return MemoryStore.INSTANCE.update(idOldUser, user);
    }

    /**
     * Метод удаления пользователя
     * @param userId - id удаляемого пользователя
     * @return true если пользователь с таким id есть и он удален
     */
    public final boolean delete(int userId) {
        return MemoryStore.INSTANCE.delete(userId);
    }

    /**
     * Метод, возращающий коллекцию всех пользователей
     */
    public final HashMap<Integer, User> findAll() {
        return MemoryStore.INSTANCE.findAll();
    }

    /**
     * Метод, возращающий пользователя по id
     * @param userId
     */
    public final User findById(int userId) {
        return MemoryStore.INSTANCE.findById(userId);
    }
}
