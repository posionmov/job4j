package ru.job4j.taskoop;

public class InputForTests extends Input {

    private String[] value;
    private int positionInValue = 0;

    public InputForTests(String[] value) {
        this.value = value;
    }

    @Override
    public String readConsole(String question) {
        return this.value[positionInValue++];
    }
}
