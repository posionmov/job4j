package ru.job4j.tictactoe;
import java.util.function.Predicate;

/**
 * Class for declarate logic in game
 * @author Galanov Sergey (parts: isWinnerX(), isWinnerO(), hasGap())
 * @since 25.07.2018
 * @version 1.1
 */
public class Logic3T {
    private final Figure3T[][] table;

    /**
     * func for filling bord
     * @param predicate
     * @param startX - starting X position
     * @param startY - starting Y position
     * @param deltaX - bias on X vector
     * @param deltaY - bias on Y vector
     * @return result (true or false) about filling line of only one figure
     */
    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Constructor of these class
     * @param table
     */
    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * func for calculating all probabilitys if player X is winner
     * @return result (true or false) about player X (he is winner or not)
     */
    public boolean isWinnerX() {
        return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1)
                || this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, 0, -1)
                || this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, this.table.length - 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 1, 0, 0, 1);
    }

    /**
     * func for calculating all probabilitys if player O is winner
     * @return result (true or false) about player O (he is winner or not)
     */
    public boolean isWinnerO() {
        return this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1)
                || this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, 0, -1)
                || this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, this.table.length - 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 1, 0, 0, 1);
    }

    /**
     * func for finding all empty cells on the board
     * @return result (true or false) about availability empty cells
     */
    public boolean hasGap() {
        boolean haveGap = false;
        for (int index = 0; index < this.table.length; index++) {
            for (int column = 0; column < this.table.length; column++) {
                if (!this.table[index][column].hasMarkO() || !this.table[index][column].hasMarkX()) {
                    haveGap = true;
                }
            }
        }
        return haveGap;
    }
}