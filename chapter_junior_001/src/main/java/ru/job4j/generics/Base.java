package ru.job4j.generics;

/**
 * Абстрактный класс, описывающий инициализацию и гетер единственного плня id
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.0
 */
public abstract class Base {

    private final String id; // Внутреннее поле, содержащее id обьекта

    /**
     * Конструктор класса
     * Задает значение id для обьекта
     * @param id - значение id для обьекта
     */
    public Base(String id) {
        this.id = id;
    }

    /**
     * Возращает id данного обьекта
     * @return id
     */
    public String getId() {
        return this.id;
    }

}
