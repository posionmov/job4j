package ru.job4j.tictactoe;
import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

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

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

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

    public boolean hasGap() {
        boolean haveGap = false;
        //return !(!isWinnerX() && !isWinnerO());
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