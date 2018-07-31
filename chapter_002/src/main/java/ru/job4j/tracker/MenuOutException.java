package ru.job4j.tracker;

/**
 * Класс для отображения исключительной ситуации там, где нам необходимо
 * @author Galanov Sergey
 * @since 31.07.2018
 * @version 1.0
 */
public class MenuOutException extends RuntimeException {

    /**
     * Конструктор данного класса
     * @param msg - сообщение об ошибке, которое нужно вывести в консоль
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
