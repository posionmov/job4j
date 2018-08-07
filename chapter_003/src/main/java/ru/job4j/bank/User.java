package ru.job4j.bank;

/**
 * Класс, описывающий каждого пользователя
 * @author Galanov Sergey
 * @since 07.08.2018
 * @version 1.0
 */
public class User {

    /**
     * Содержит приватные поля
     */
    private String name; // Имя
    private String passport; // Паспортные данные


    /**
     * Конструктор данного класса
     * @param name - имя
     * @param passport - Паспортные данные
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Метод-геттер паспортных данные
     * @return паспортные данные
     */
    public String getPassport() {
        return this.passport;
    }
}
