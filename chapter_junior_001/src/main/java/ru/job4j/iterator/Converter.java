package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс для конвертации нескольких последовательностей в одну
 * @author Galanov Sergey
 * @since 12.08.2018
 * @version 1.0
 */
public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        /**
         * Анонимный класс иетратора итераторов
         */
        return new Iterator<Integer>() {

            /**
             * Содержит внутреннее поле с ссылкой на текущий итератор
             */
            Iterator<Integer> curIterator = it.next();

            /**
             * Реализация метода интерфейса
             * @return true - если у за текущим элементов есть еще один элемент
             */
            @Override
            public boolean hasNext() {
                return find();
            }

            /**
             * Реализация метода интерфейса
             * @return значение из текущего итератора и передвигает указатель дальше
             */
            @Override
            public Integer next() {
                Integer result;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                result = curIterator.next();
                return result;
            }

            /**
             * Вспомогательный метод
             * Производит корректное сдвжение указателя, а также возращает true если далее есть еще элементы
             * @return true если далее есть еще элементы
             */
            public boolean find() {
                boolean isExist = false;
                if (!curIterator.hasNext() && it.hasNext()) {
                    curIterator = it.next();
                }
                if (curIterator.hasNext()) {
                   isExist = true;
                }
                return isExist;
            }

        };
    }

}
