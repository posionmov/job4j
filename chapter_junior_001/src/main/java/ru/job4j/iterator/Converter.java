package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {

            Iterator<Integer> iterator = it.next();


            @Override
            public boolean hasNext() {
                return iterator != null;
            }

            @Override
            public Integer next() {
                if (!iterator.hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer result = iterator.next();
                if (!iterator.hasNext()) {
                    iterator = it.hasNext() ? it.next() : null;
                }

                return result;
            }
        };
    }

}
