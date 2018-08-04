package ru.job4j.taskoop;

/**
 * Интерфейс, который описывает какие функции должен иметь каждый наследник от данного класса
 * @author Galanov Sergey
 * @since 04.08.2018
 * @version 1.0
 */
public interface InputInterface {


    int userChoise(String question, int quantity);
}
