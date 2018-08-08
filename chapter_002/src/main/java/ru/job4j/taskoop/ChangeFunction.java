package ru.job4j.taskoop;

import java.util.Arrays;

/**
 * Класс, содержащий метод выдачи сдачи в зависимости от внесенных денег
 */
public class ChangeFunction {

    /**
     * Метод выдачи сдачи в зависимости от внесенных денег
     * @param value - величина внесенных денег
     * @param price - цена товара
     * @return массив, содержащий все монеты, которые должны пойти на сдачу
     */
    public int[] change(int value, int price) {
        int[] result = new int[100];
        int[] curMoney = new int[] {1, 2, 5, 10};
        int resultSum = value - price;
        String answer = value >= price ? String.format("Ваша сдача: %s", resultSum) : String.format("Вам не хватает: %s", 0 - resultSum);
        System.out.println(answer);
        int valueInArray = 0;
        if (value > price) {
            for (int i = curMoney.length - 1; i >= 0; i--) {
                if (resultSum >= curMoney[i]) {
                    for (int iter = 0; iter != (resultSum / curMoney[i]); iter++) {
                        result[valueInArray++] = curMoney[i];
                    }
                    resultSum -= curMoney[i] * (resultSum / curMoney[i]);
                }
            }
        }
        return Arrays.copyOf(result, valueInArray);
    }

}
