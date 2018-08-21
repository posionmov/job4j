package ru.job4j.exam;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для сравнения двух строк при помощи HashMap
 * @author Galanov Sergey
 * @since 21.08.2018
 * @version 1.0
 */
public class CompareStrings {

    /**
     * Метод, проверяющий равенство строк в разрезе одинакового количества и качества символов
     * Т.е. если у строк одни и те же символы, но разный их порядок, то они все равно считаются одинаковыми
     * Если длины строк не равны, то возращает false
     * Если длины строк равны, то добавляет каждую строку в хэшмапу
     * Затем сравнивает каждый элемент из первой хэшмапы со второй хэшмапой
     * Так как в методе get используется поиск номера элемента по хэшкоду, то элемент находится за константное время
     * Если в ячейке, полученной в результате вычисления хэшкода и "побитового И" во втором массиве находится другое
     *                                                                                  значение, то возращает false
     *
     * Если строки равны, то возращает true
     * Получение каждого элемента из хэшмапы константно, однако время выполнения так же зависит от длины слов
     * @param one - первая строка
     * @param two - втрая строка
     * @return true если строки равны, false если строки разные
     */
    public boolean compareString(String one, String two) {
        boolean result = false;
        if (one.length() == two.length()) {
            Map<Character, Integer> mapOne = new HashMap<>();
            for (int i = 0; i < one.length(); i++) {
                if (!mapOne.containsKey(one.charAt(i))) {
                    mapOne.put(one.charAt(i), 1);
                } else {
                    mapOne.put(one.charAt(i), mapOne.get(one.charAt(i)) + 1);
                }
            }
            Map<Character, Integer> mapTwo = new HashMap<>();
            for (int i = 0; i < two.length(); i++) {
                if (!mapTwo.containsKey(two.charAt(i))) {
                    mapTwo.put(two.charAt(i), 1);
                } else {
                    mapTwo.put(one.charAt(i), mapTwo.get(one.charAt(i)) + 1);
                }
            }
            for (int i = 0; i < mapOne.size(); i++) {
                result = true;
                if (!mapOne.get(one.charAt(i)).equals(mapTwo.get(one.charAt(i)))) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
