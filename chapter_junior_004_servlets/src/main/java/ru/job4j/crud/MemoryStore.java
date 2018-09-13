package ru.job4j.crud;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс, реализующий интерфейс Store
 * Реализует хранение пользователей внутри своей потокобезопасной коллекции
 * @author Galanov Sergey
 * @since 13.09.2018
 * @version 1.0
 */
public class MemoryStore implements Store {

    /**
     * Приватное поле класса
     * Содержит:
     *  - обьект данного класса, который создается при инициализации программы
     *  - потокобезопасный List
     *
     */
    private static MemoryStore memoryStore = new MemoryStore();
    private ConcurrentHashMap<Integer, User> users;

    /**
     * Дефолтный конструктор данного класса
     * Имеет приватный уровень доступа - это необходимо для предотвращения создания обьектов данного класса
     */
    private MemoryStore() {
        users = new ConcurrentHashMap<>();
    }

    /**
     * Метод, возращающий обьект данного класса
     * @return - обьект данного класса
     */
    public static MemoryStore getInstance() {
        return memoryStore;
    }

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
        if (!this.users.contains(user)) {
            System.out.println("Добавляю пользователя с именем " + user.getName());
            this.users.putIfAbsent(user.getId(), user);
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
    public boolean update(int userId, String name, String login, String email) {
        boolean result = false;
        User user = this.findById(userId);
        if (!(user == null)) {
            System.out.println("Начинаю обновлять данные пользователя");
            user.setName(name);
            user.setLogin(login);
            user.setEmail(email);
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
        return new HashMap<Integer, User>(this.users);
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
}
