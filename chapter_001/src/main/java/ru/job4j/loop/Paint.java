package ru.job4j.loop;
import java.util.function.BiPredicate;

/**
 * Class for painting pyramid
 * @author Galanov Sergey
 * @since 23.07.2018
 * @version 2.0
 */
public class Paint {

    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    public String pyramid(int height) {
        return this.loopBy(height, 2 * height - 1, (row, column) -> row >= height - column - 1 && row + height - 1 >= column);
    }


    public String rightTrl(int height) {
        return this.loopBy(height, height, (row, column)-> row >= column);
    }

    public String leftTRL(int height) {
        return this.loopBy(height, height, (row, column) -> row >= height - column - 1);
    }
}
