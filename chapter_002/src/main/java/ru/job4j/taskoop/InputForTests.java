package ru.job4j.taskoop;

/**
 * Класс-наследник от стандарного ввода данных
 * предназачен для тестов работы
 * @author Galanov Sergey
 * @since 04.08.2018
 * @version 1.0
 */
public class InputForTests extends Input {

    /**
     * Содержит массив ввода даных в консоль и текущая позиция в массиве
     */
    private String[] value;
    private int positionInValue = 0;

    /**
     * Конструктор данного класса
     * @param value - значения для автоматического ввода в консоль
     */
    public InputForTests(String[] value) {
        this.value = value;
    }

    /**
     * Переопределеный метод, который должен имитирова ввод данных от пользователя
     * @param question - вопрос, который задается пользователю в консоли
     * @return
     */
    @Override
    public String readConsole(String question) {
        return this.value[positionInValue++];
    }
}
