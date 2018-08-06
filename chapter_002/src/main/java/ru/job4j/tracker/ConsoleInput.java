package ru.job4j.tracker;
import java.util.*;

/**
 * Class for reading from user chose
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public int ask(String question, List<Integer> range) {
        int result = Integer.valueOf(this.ask(question));
        boolean isExist = false;
        for (int value : range) {
            if (result == value) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            throw new MenuOutException("Введено число, которое больше количество пунктов меню");
        }
        return result;
    }

}
