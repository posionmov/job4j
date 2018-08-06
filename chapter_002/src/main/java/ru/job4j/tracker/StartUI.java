package ru.job4j.tracker;

import java.util.List;

/**
 * Class for placing, editing and deleting bids
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.7
 */
public class StartUI {

    /**
     * Contains private variable:
     * input - final object of class ConsoleInput
     * tracker - final object of class Tracker
     * range - Коллекция значений пунктов меню
     */
    private final ValidateInput input;
    private final Tracker tracker;
    private List<Integer> range;
    private boolean isWorking = true;

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
        this.range = menu.fillActions(this);
        do {
            menu.show();
            menu.select(input.ask("Введите номер интересующей Вас операции: ", this.range));
        } while (isWorking);
        System.out.println("Выключение");
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }

    public void isOver() {
        this.isWorking = false;
    }
}

