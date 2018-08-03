package ru.job4j.taskoop;

import java.util.Arrays;

/**
 * Класс-обертка над int[]
 * Производит выдачу сдачи
 */
public class Change {

    private final int[] curMoney = new int[] {1, 2, 5, 10};

    public int[] change(int value, int price) {
        int[] result = new int[100];
        int resultSum = value - price;
        String answer = value > price ? String.format("Ваша сдача: %s", resultSum) : String.format("Вам не хватает: %s", 0 - resultSum);
        System.out.println(answer);
        int valueInArray = 0;
        if (value > price) {
            for (int i = this.curMoney.length - 1; i >= 0; i--) {
                if (resultSum >= this.curMoney[i]) {
                    for (int iter = 0; iter != (resultSum / curMoney[i]); iter++) {
                        result[valueInArray++] = this.curMoney[i];
                    }
                    resultSum -= curMoney[i] * (resultSum / curMoney[i]);
                }
            }
        }
        return Arrays.copyOf(result, valueInArray);
    }
}
