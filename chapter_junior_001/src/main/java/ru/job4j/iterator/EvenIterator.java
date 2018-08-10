package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {

    private final int[] array;
    private int curIndex = 0;

    public EvenIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = curIndex; i < array.length; i++) {
            if (this.array[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        while (this.array[curIndex] % 2 != 0) {
            curIndex++;
            if (curIndex == this.array.length) {
                throw new NoSuchElementException();
            }
        }
        return this.array[curIndex++];
    }
}
