package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирования импровизированного древа
 * @author Galanov Sergey
 * @since 17.08.2018
 * @version 1.1
 */
public class SimpleTreeRealiseTest {

    /**
     * Тест, возращающий true если значение есть в древе при условии, что проверяемый обьект действительно есть в древе
     */
    @Test
    public void when6ElFindLastThen6() {
        SimpleTreeRealise<Integer> tree = new SimpleTreeRealise<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent(), is(true)
        );
    }

    /**
     * Тест, возращающий true если значение есть в древе при условии, что проверяемого обьекта нет в древе
     */
    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        SimpleTreeRealise<Integer> tree = new SimpleTreeRealise<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7).isPresent(), is(false)
        );
    }

    /**
     * Тест на бинарство дерева
     */
    @Test
    public void whenTreeIsBinaryThenReturnTrue() {
        SimpleTreeRealise<Integer> tree = new SimpleTreeRealise<>(2);
        tree.add(2, 3);
        tree.add(2, 4);
        tree.add(3, 5);
        tree.add(3, 6);
        tree.add(4, 7);
        assertThat(tree.isBinary(), is(true));
    }

    /**
     * Тест на отсутствие бинарство дерева
     */
    @Test
    public void whenTreeIsNotBinaryThenReturnFalse() {
        SimpleTreeRealise<Integer> tree = new SimpleTreeRealise<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary(), is(false));
    }

    /**
     * Тест на более сложное дерево, где бинарность нарушается в середине
     */
    @Test
    public void whenTreeIsNotBinaryInOneElementThenFalse() {
        SimpleTreeRealise<Integer> tree = new SimpleTreeRealise<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(4, 8);
        tree.add(4, 9);
        tree.add(5, 10);
        tree.add(5, 11);
        tree.add(5, 12);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary(), is(false));
    }

}