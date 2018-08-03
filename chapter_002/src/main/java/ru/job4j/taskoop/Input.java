package ru.job4j.taskoop;
import java.util.Scanner;

/**
 * Класс для запроса ввода данных от пользователя
 */
public class Input {

    private Scanner scanner = new Scanner(System.in);

    public String readConsole(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    public int userChoise(String question, int quantity) {
        int result = Integer.valueOf(this.readConsole(question));
        boolean isExist = false;
        for (int i = 0; i != quantity; i++) {
            if (result == i) {
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
