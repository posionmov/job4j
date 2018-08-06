package ru.job4j.sort;

/**
 * Класс, описывающий поля пользователя
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.0
 */
public class User implements Comparable<User> {

    /**
     * Содержит поля класса
     */
    private String name; // Имя
    private int age; // Возраст

    /**
     * Конструктор данного класса
     * @param name - вводимое имя
     * @param age - вводимый возраст
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Переопределенный метод, сравнивающий два значения
     * @param o
     * @return
     */
    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.age);
    }
}
