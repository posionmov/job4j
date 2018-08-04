package ru.job4j.taskoop;

import java.util.Arrays;

/**
 * Класс, производящий расчет сдачи (если денег больше, чем цена)
 * @author Galanov Sergey
 * @since 04.08.2018
 * @version 1.0
 */
public class Change {

    /**
     * Поле содержит типы выдаваемых монет
     */
    private final int[] curMoney = new int[] {1, 2, 5, 10};

    /**
     * Метод выдачи сдачи
     * Если денег больше, чем цена, то происходит расчет и формируется массив, содержащий наиболее рациональный способ выдачи
     * Если денге меньше, то выводится сообщение о том, сколько денег не хватает
     * @param value - количество полученных дене
     * @param price - цена конкретного напитка
     * @return массив, содержащий монеты. Даный массив содержит наименьшее количество монет
     */
    public int[] change(int value, int price) {
        int[] result = new int[100];
        int resultSum = value - price;
        String answer = value >= price ? String.format("Ваша сдача: %s", resultSum) : String.format("Вам не хватает: %s", 0 - resultSum);
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
