package ru.job4j.comporator;

import java.util.Comparator;

/**
 * Класс, реализующий интерфейс компоратора
 * @author Galanov Sergey
 * @since 07.08.2018
 * @version 1.1
 */
public class ListCompare implements Comparator<String> {

    /**
     * Переопределенный метод компоратора строк
     * Принимает две строки и сравнивает пожлементно каждый символ в них
     * @param o1 - первая строка
     * @param o2 - вторая строка
     * @return кузультат:
     *                      0 - они равны
     *                     -1 - вторая тсрока больше первой
     *                      1 - первая строка больше второй
     */
    @Override
    public int compare(String o1, String o2) {
        int result = Integer.compare(o1.length(), o2.length());
        int minLength = o1.length() > o2.length() ? o2.length() : o1.length();
        for (int i = 0; i < minLength; i++) {
            int temp = Character.compare(o1.charAt(i), o2.charAt(i));
            if (temp != 0) {
                result = temp;
                break;
            }
        }
        return result;
    }
}
