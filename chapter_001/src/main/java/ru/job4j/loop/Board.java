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

        String x = "X";
        String nothing = " ";
        String tmp = "";

        for (int i = 1; i <= width; i++) {
            if (i % 2 == 0) {
                tmp = nothing;
                nothing = x;
                x = tmp;
            } else {
                x = "X";
                nothing = " ";
                tmp = "";
            }
            for (int j = 1; j <= height; j++) {

                if (j % 2 == 0) {
                        screen.append(nothing);
                    } else if (j % 2 != 0) {
                        screen.append(x);
                        if (j == height) {
                            screen.append((ln));
                        }
                    }

            }
        }
        return screen.toString();
    }
}
