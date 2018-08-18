package ru.job4j.maps;

import ru.job4j.lists.DynamicArrayLinkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, имитирующий поведение HashMap
 * @author Galanov Sergey
 * @since 18.08.2018
 * @version 1.2
 * @param <K> обьект любого класса, используется как ключ
 * @param <V> обьект любого класса, отличного от К, используется как значение
 */
public class SimpleHashMap<K, V> implements Iterable<K> {

    /**
     * Приватные поля класса
     */
    private Element[] array = new Element[16]; // текущий массив
    private int curLength = 0; //Текущая длина

    /**
     * Метод для получения текущей длины массива
     * @return
     */
    public int getCurLength() {
        return this.curLength;
    }

    /**
     * Метод, который добавляет ключ и значение в импровизированную хэш мапу
     * @param key вставляемый ключ
     * @param value Вставляемое значение
     * @return true, если ключ был уникальным и обьект был вставлен
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if (checkUnique(key)) {
            Element<K, V> element = new Element<>(key, value);
            //element.put(value);
            this.array[this.calculateIndexOfElement(key.hashCode())] = element;
            this.curLength++;
            this.resize();
            result = true;
        }
        return result;
    }

    /**
     * Метод, пробегающий по всему массиву и проверяющий ключи
     * @param key ключ, уникальность значения которого требуется определить
     * @return true если такого ключа нет
     */
    private boolean checkUnique(K key) {
        boolean result = true;
        if (this.array[this.calculateIndexOfElement(key.hashCode())] != null) {
            result = false;
        }

        return result;
    }

    /**
     * Метод, возращающий значение по ключу
     * @param key ключ, принадлежащий конкретному элементу
     * @return значение, которое было найдено п оключу
     */
    public V get(K key) {
        return (V) this.array[this.calculateIndexOfElement(key.hashCode())].getValue();
    }

    /**
     * Метод, удаляющий пару ключ-значение
     * @param key ключ для поиска элемента
     * @return false если данного ключа нет
     */
    public boolean delete(K key) {
        boolean result = false;

        int position = this.calculateIndexOfElement(key.hashCode());
        if (this.array[position] != null) {
            this.array[position] = null;
            result = true;
            this.curLength--;
        }
        return result;
    }

    /**
     * Метод, вычисляющий положение в массиве
     * @param hash результат вычисления хэш кода
     * @return индекс элемента в массиве
     */
    private int calculateIndexOfElement(int hash) {
        return hash & (this.array.length - 1);
    }

    /**
     * Метод, производящий перераспределение всех элементов при увеличении длины массива
     */
    private void resize() {
        if (isFulled()) {
            this.array = Arrays.copyOf(this.array, this.array.length * 2);
        }
    }

    /**
     * Метод, проверяющий наполненность
     */
    private boolean isFulled() {
        return  (this.curLength / this.array.length) >= 0.75;
    }

    /**
     * Метод интерфейса Iterable
     * @return обьект класса Iterator
     */
    @Override
    public Iterator iterator() {
        return new Iterator<V>() {
            private int curIndex = 0;

            @Override
            public boolean hasNext() {
                return this.isNext();
            }

            @Override
            public V next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                V result = null;
                for (; curIndex < array.length; curIndex++) {
                    if (array[curIndex] != null) {
                        result = (V) array[curIndex++].getValue();
                        break;
                    }
                }
                return result;
            }

            private boolean isNext() {
                return this.curIndex <= curLength;
            }
        };
    }

    /**
     * Приватный класс, описывающий связку ключ-значение
     */
    private class Element<K, V> {

        private K key;
        private V value;
        private Element<K, V> next;

        public Element(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }


//
//        /**
//         * Содержит поля класса:
//         * - связанный список
//         * - текущий индекс значения
//         */
//        private DynamicArrayLinkedList<Object> arrayForIterator = new DynamicArrayLinkedList<>();
//        private int curValueIndex = 1;
//        private K key;
//
//        public Element(K key) {
//            this.key = key;
//        }
//
//        /**
//         * Метод возращает текущее значение из списка
//         * @return значение из списка
//         */
//        V getValue() {
//            return (V) this.arrayForIterator.get(1);
//        }
//
//        /**
//         * Метод, который "кладет" значени в список если его там еще нет
//         * @param value значение, которое следует положить в список
//         */
//        void put(V value) {
//            if (!this.checkValue(value)) {
//                this.arrayForIterator.add(value);
//                this.curValueIndex += 2;
//            }
//        }
//
//        /**
//         * Метод, проверяющий наличие в данном списке конкретного значения
//         * @param value проверяемое значение
//         * @return true если значение есть
//         */
//        private boolean checkValue(V value) {
//            boolean result = false;
//            for (int i = 1; i < this.curValueIndex; i += 2) {
//                if (this.arrayForIterator.get(i).equals(value)) {
//                    result = true;
//                    break;
//                }
//            }
//            return result;
//        }
    }
}
