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

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

}
