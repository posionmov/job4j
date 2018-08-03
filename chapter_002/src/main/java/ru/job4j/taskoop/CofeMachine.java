package ru.job4j.taskoop;

public class CofeMachine {

    private InputManager inputManager;
    private boolean isOver = false;
    private ValidateInput input;
    private int valueOfChoices;
    private Change changer;

    public CofeMachine(ValidateInput input) {
        this.input = input;
    }

    public static void main(String[] args) {
        new CofeMachine(new ValidateInput(new Input())).init();
    }

    public void init() {
        this.inputManager = new InputManager(input);
        this.inputManager.getAllCoffes();
        this.changer = new Change();


        do {
            this.inputManager.showMenu();
            this.inputManager.doWork(this.input.userChoise("Что вы выбираете?", inputManager.getValueOfItems()));

        } while (!isOver);
    }



}
