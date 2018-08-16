package ru.job4j.maps;

import ru.job4j.lists.DynamicArrayLinkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, имитирующий поведение HashMap
 * @author Galanov Sergey
 * @since 16.08.2018
 * @version 1.0
 * @param <K> обьект любого класса, используется как ключ
 * @param <V> обьект любого класса, отличного от К, используется как значение
 */
public class SimpleHashMap<K, V> implements Iterable {

    /**
     * Приватные поля класса
     */
    private Element[] array = new Element[10]; // текущий массив
    private int curIndex = 0; // Текущий индекс
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
            Element element = new Element();
            element.put(key, value);
            this.array[this.curIndex] = element;
            this.curIndex++;
            if (this.curIndex == this.array.length) {
                this.array = Arrays.copyOf(this.array, this.array.length * 2);
            }
            this.curLength++;
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
        for (int i = 0; i < this.curLength; i++) {
            if (this.array[i].getKey().equals(key)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Метод, возращающий значение по ключу
     * @param key ключ, принадлежащий конкретному элементу
     * @return значение, которое было найдено п оключу
     */
    public V get(K key) {
        V result = null;
        for (int i = 0; i < this.curLength; i++) {
            if (this.array[i].getKey().equals(key)) {
                result = (V) this.array[i].getValue();
            }
        }
        return result;
    }

    /**
     * Метод, удаляющий пару ключ-значение
     * @param key ключ для поиска элемента
     * @return false если данного ключа нет
     */
    public boolean delete(K key) {
        boolean result = false;
        for (int i = 0; i < this.curLength; i++) {
            if (this.array[i].getKey().equals(key)) {
                System.arraycopy(this.array, i + 1, this.array, i, this.array.length - i - 1);
                this.curLength--;
                result = true;
            }
        }
        return result;
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
                return (V) array[curIndex++].getValue();
            }

            private boolean isNext() {
                return this.curIndex != curLength;
            }
        };
    }

    /**
     * Приватный класс, описывающий связку ключ-значение
     */
    private class Element<K, V> {
        DynamicArrayLinkedList<Object> arrayForIterator = new DynamicArrayLinkedList<>();

        K getKey() {
            return (K) this.arrayForIterator.get(1);
        }

        V getValue() {
            return (V) this.arrayForIterator.get(0);
        }

        void put(K key, V value) {
            this.arrayForIterator.add(value);
            this.arrayForIterator.add(key);
        }
    }
}
