package ru.job4j.tree;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирования импровизированного древа
 * @author Galanov Sergey
 * @since 17.08.2018
 * @version 1.0
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

}