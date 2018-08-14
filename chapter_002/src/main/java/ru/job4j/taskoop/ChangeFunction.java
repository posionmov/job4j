package ru.job4j.taskoop;

import java.util.Arrays;

/**
 * Класс, содержащий метод выдачи сдачи в зависимости от внесенных денег
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.3
 */
public class ChangeFunction {

    private int[] curMoney = new int[] {1, 2, 5, 10}; // Список типов всех монет

    /**
     * Метод выдачи сдачи в зависимости от внесенных денег
     * @param value - величина внесенных денег
     * @param price - цена товара
     * @return массив, содержащий все монеты, которые должны пойти на сдачу
     */
    public int[] change(int value, int price) {
        int[] result = new int[0];
        int resultSum = value - price;
        String answer = value >= price ? String.format("Ваша сдача: %s", resultSum) : String.format("Вам не хватает: %s", 0 - resultSum);
        System.out.println(answer);
        if (value > price) {
            result = this.calculateChange(resultSum);
        }
        return result;
    }

    /**
     * Метод подсчета сдачи
     * @param sumForChange - Сумма, которую требуется дать в сдаче
     * @return массив монет, которые наиболее рационально могут дать сдачи
     */
    private int[] calculateChange(int sumForChange) {
        int[] result = new int[100];
        int valueInArray = 0;
        for (int i = this.curMoney.length - 1; i >= 0; i--) {
            if (sumForChange >= this.curMoney[i]) {
                for (int iter = 0; iter != (sumForChange / this.curMoney[i]); iter++) {
                    result[valueInArray++] = this.curMoney[i];
                }
                sumForChange -= this.curMoney[i] * (sumForChange / this.curMoney[i]);
            }
        }
        return Arrays.copyOf(result, valueInArray);
    }

}
