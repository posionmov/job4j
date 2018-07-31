package ru.job4j.tracker;

/**
 *  Класс для проверки корректности вводимых данных
 * @author Galanov Sergey
 * @since 31.07.2018
 * @version 1.0
 */
public class ValidateInput extends ConsoleInput {

    /**
     * Функция, которая переопределяет метод родителя и проверяет на правильность вводимых данных
     * @param question - вопрос, который будет отображен в консоли
     * @param range - массив возможных ответов
     * @return - вводимый пользователем ответ, но только если он выполняет условия
     */
    public int ask(String question, int[] range) {
        boolean invalidNumber = true;
        int result = -1;
        do {
            try {
                result = super.ask(question, range);
                invalidNumber = false;
            } catch (MenuOutException moe) {
                System.out.println("Пожалуйста введите число из меню");
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста введите число");
            }
        } while (invalidNumber);
        return result;
    }
}
