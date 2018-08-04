package ru.job4j.taskoop;

/**
 * Класс, реализующий интерфейс ввода данных
 * Так же данный класс проверяет корректность воода данных от пользовтеля и ловит ошибки
 * @author Galanov Sergey
 * @since 04.08.2018
 * @version 1.0
 */
public class ValidateInput implements InputInterface {

    /**
     * Ссылка на ввод данных в консоль
     */
    private InputInterface input;

    /**
     * Конструктор данного класса
     * @param input - ввод данных в консоль
     */
    public ValidateInput(InputInterface input) {
        this.input = input;
    }

    /**
     * Проверка вводимых данных в консоль
     * Если введено число, котрое больше количества пунктов в меню - то выведется предложение ввести корректное число
     * Если введено не число, выведется сообщение о том, что нужно ввести число
     * @param question - вопрос пользователю
     * @param quantity - максимальное число, которое можно ввести в консоль
     * @return корректный ввод пользователя
     */
    public int userChoise(String question, int quantity) {
        boolean isCorrect = false;
        int result = -1;
        do {
            try {
                result = input.userChoise(question, quantity);
                isCorrect = true;
            } catch (OutFromMenuException ofme) {
                System.out.println("Введите число, которое есть в меню.");
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста, введите число.");
            }
        } while (!isCorrect);
        return result;
    }

}
