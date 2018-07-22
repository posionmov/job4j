package ru.job4j.loop;

/**
 * Class for leaason about chess
 * @author Galanov Sergey
 * @since 21.07.2018
 * @version 1.0
 */
public class Board {

    /**
     * Class for painting chess board in console
     * @param width - width of chess board
     * @param height - height of chess board
     * @return String of complete chess board
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 1; i <= width; i++) {
            for (int j = 1; j <= height; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
                if (j == height) {
                    screen.append((ln));
                }
            }
        }
        return screen.toString();
    }
}
