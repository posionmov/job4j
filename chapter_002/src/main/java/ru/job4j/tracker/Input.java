package ru.job4j.tracker;

import java.util.List;

/**
 * Interface for input classes
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public interface Input {
    String ask(String questions);

    int ask(String question, List<Integer> range);
}
