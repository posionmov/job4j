package ru.job4j.taskoop;

public class ValidateInput extends Input {

    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String readConsole(String question) {
        return this.input.readConsole(question);
    }

    @Override
    public int userChoise(String question, int quantity) {
        boolean isCorrect = false;
        int result = -1;
        do {
            try {
                result = super.userChoise(question, quantity);
                isCorrect = true;
            } catch (OutFromMenuException ofme) {
                System.out.println("Введите число, которое означает пункт из меню.");
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста, введите число.");
            }
        } while (!isCorrect);
        return result;
    }

}
