package ru.job4j.comporator;

import java.util.Comparator;

/**
 * Класс, реализующий интерфейс компоратора
 * @author Galanov Sergey
 * @since 07.08.2018
 * @version 1.0
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
        int result = 0;
        for (int i = 0; i != o1.length(); i++) {
            if (Integer.compare((int) o1.charAt(i), (int) o2.charAt(i)) != 0) { // Сравнивает не буквы, а их числовое значение
                result = Integer.compare((int) o1.charAt(i), (int) o2.charAt(i));
                break;
            }
            if (o1.length() != o2.length() && i == o1.length() - 1) {
                result = o1.length() > o2.length() ? 1 : -1;
            }
        }
        return result;
    }
}
