package ru.job4j.generics;

/**
 * Класс - наследник от класса Base
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.0
 */
public class Role extends Base {

    /**
     * Конструктор класса, который вызывает конструктор своего родителя
     * @param id идентификатор обьекта данного класса
     */
    public Role(String id) {
        super(id);
    }
}
