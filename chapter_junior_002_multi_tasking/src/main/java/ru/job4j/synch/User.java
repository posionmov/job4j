package ru.job4j.synch;

/**
 * Класс, описывающий пользователя
 * @author Galanov Sergey
 * @since 23.08.2018
 * @version 1.0
 */
public class User {

    /**
     * Внутренние поля класса
     * Содержат id пользователя
     *  и количество денег на счете (amount)
     */
    private int id;
    private int amount;

    /**
     * Конструктор класса
     * @param id пользователя
     * @param amount - количество денег пользователя
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Метод, устанавливающий новое количество денег у данного пользователя
     * @param newAmount - новое количество денег
     */
    public void setAmount(int newAmount) {
        this.amount = newAmount;
    }

    /**
     * Геттер id пользователя
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * Геттер количества денег пользователя
     * @return
     */
    public int getAmount() {
        return this.amount;
    }
}
