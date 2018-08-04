package ru.job4j.taskoop;
import java.util.Scanner;

/**
 * Класс для запроса ввода данных от пользователя
 * @author Galanov Sergey
 * @since 04.08.2018
 * @version 1.0
 */
public class Input implements InputInterface {

    /**
     * Содержит ссылку на ввод данх из консоли
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Осуществляет вывод в консоль вопроса и возращает ответ пользователя
     * @param question - вопрос, который задается пользователю в консоли
     * @return ответ пользователя
     */
    public String readConsole(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Осуществляет обработку данных от пользователя
     * @param question - вопрос пользователю
     * @param quantity - максимальный рамер ответ пользователя (от 1 до quantity)
     * @return выбор пользователя, но только если он правильный, иначе кидает в исключительную ситуацию
     */
    public int userChoise(String question, int quantity) {
        int result = Integer.valueOf(this.readConsole(question));
        boolean isExist = false;
        for (int i = 0; i != quantity; i++) {
            if (result == i && result > 0) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            throw new OutFromMenuException("Введено число больше, чем есть вариантов в автомате");
        }
        return result;
    }

}
