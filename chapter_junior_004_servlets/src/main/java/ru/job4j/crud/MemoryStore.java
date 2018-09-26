package ru.job4j.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс, реализующий интерфейс Store
 * Реализует хранение пользователей внутри своей потокобезопасной коллекции
 * @author Galanov Sergey
 * @since 18.09.2018
 * @version 1.1
 */
public enum  MemoryStore implements Store {

    /**
     * Приватное поле класса
     * Содержит:
     *  - инстанс
     *  - потокобезопасная мапа
     */
    INSTANCE;
    private ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    /**
     * Метод добавления в потокобезопасную коллекцию пользователя
     * Добавляет только в том случае, если такого обьекта в коллекции нет
     * Для этого переопределен метод equals и hashCode в классе User
     * @param user - обьект класса User, которого необходимо добавить в коллекцию
     * @return true - если такого пользователя небыло
     */
    @Override
    public boolean add(User user) {
        boolean result = false;
        if (this.users.putIfAbsent(user.getId(), user) == null) {
            System.out.println("Добавляю пользователя с именем " + user.getName());
            result = true;
        }
        return result;
    }

    /**
     * Метод, обновляющий пользователя в коллекции.
     * Поиск пользователя будет производится по id
     * @return true если пользователь обновлен
     */
    @Override
    public boolean update(int id, User newUser) {
        boolean result = false;
        User user = this.findById(id);
        if (!(user == null)) {
            System.out.println("Начинаю обновлять данные пользователя");
            user.setName(newUser.getName());
            user.setLogin(newUser.getLogin());
            user.setEmail(newUser.getEmail());
            result = true;
        }
        return result;
    }

    /**
     * Метод удаления из коллекции пользователя по id
     * @param userId
     * @return true если такого id нет
     */
    @Override
    public boolean delete(int userId) {
        System.out.println("Удаляю пользователя с id - " + userId);
        User user = this.users.remove(userId);
        return !(user == null);
    }

    /**
     * Метод, возращающий копию текущей коллекции
     * @return копия текущей коллекции
     */
    @Override
    public HashMap<Integer, User> findAll() {
        return new HashMap<>(this.users);
    }

    /**
     * Метод, производящий поиск пользователя по id
     * @param id - id искомого пользователя
     * @return - ссылка на обьект класса User
     */
    @Override
    public User findById(int id) {
        System.out.println("Ищу пользователя с id = " + id);
        return this.users.get(id);
    }

    @Override
    public boolean checkCorrect(String username, String password) {
        return false;
    }

    @Override
    public Map<Integer, String> getAllRights() {
        return null;
    }

    @Override
    public User checkUser(String userName, String userPassword) {
        return null;
    }
}
