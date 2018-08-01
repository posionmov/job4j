package ru.job4j.tracker;

/**
 * Class for placing, editing and deleting bids
 * @author Galanov Sergey
 * @since 31.07.2018
 * @version 1.6
 */
public class StartUI {

    /**
     * Contains private variable:
     * input - final object of class ConsoleInput
     * tracker - final object of class Tracker
     * range - массив значений пунктов меню
     */
    private final ValidateInput input;
    private final Tracker tracker;
    int[] range;

    /**
     * Constructor for these class
     *
     * @param input   - initializing for class ConsoleInput
     * @param tracker - initializing for class Tracker
     */
    public StartUI(ValidateInput input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Func for show and read elements of menu
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        this.range = menu.fillActions();
        int curOperation;
        do {
            menu.show();
            curOperation = menu.select(input.ask("Введите номер интересующей Вас операции: ", this.range));
        } while (curOperation != 6);
        System.out.println("Выключение");
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}

