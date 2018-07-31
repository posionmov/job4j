package ru.job4j.tracker;

/**
 * Class for placing, editing and deleting bids
 * @author Galanov Sergey
 * @since 31.07.2018
 * @version 1.5
 */
public class StartUI {

    /**
     * Contains private variable of menu
     */
    private static final int ADD = 0;
    private static final int SHOW = 1;
    private static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int FINDID = 4;
    private static final int FINDNAME = 5;
    private static final int EXIT = 6;

    /**
     * Contains private variable:
     * exit = mark for exit program
     * input - final object of class ConsoleInput
     * tracker - final object of class Tracker
     */
    private boolean exit = false;
    private final ConsoleInput input;
    private final Tracker tracker;

    /**
     * Constructor for these class
     *
     * @param input   - initializing for class ConsoleInput
     * @param tracker - initializing for class Tracker
     */
    public StartUI(ConsoleInput input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Func for show and read elements of menu
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int curOperation;
        do {
            menu.show();
            curOperation = Integer.valueOf(input.ask("Введите номер интересующей Вас операции: "));
            if (curOperation != 6) {
                menu.select(curOperation);
            }
        } while (curOperation != 6);
        System.out.println("Выключение");
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}

