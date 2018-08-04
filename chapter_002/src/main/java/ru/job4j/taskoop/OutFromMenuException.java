package ru.job4j.taskoop;

/**
 * Пользовательская ошибка
 */
public class OutFromMenuException extends RuntimeException {

    public OutFromMenuException(String msg) {
        super(msg);
    }

}
