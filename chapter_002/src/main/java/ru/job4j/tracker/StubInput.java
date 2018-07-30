package ru.job4j.tracker;

/**
 * Class for imitate input from user
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public class StubInput extends ConsoleInput {

    /**
     * Contains:
     * - array of Strings: array of actions in terminal
     *  - int: current position in array
     */
    private final String[] value;
    private int position = 0;

    /**
     * Constructor for this class
     * @param value array of actions in terminal
     */
    public StubInput(String[] value) {
        this.value = value;
    }

    /**
     * Overrride func from father class
     * @param questions - current question in terminal
     * @return value of answer from array of actions
     */
    @Override
    public String ask(String questions) {
        return this.value[position++];
    }
}
