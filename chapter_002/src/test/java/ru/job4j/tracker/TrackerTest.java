package ru.job4j.tracker;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing Tracker.java
 * @author Galanov Sergey
 * @since 27.07.2018
 * @version 1.3
 */
public class TrackerTest {

    /**
     * Test for func "add"
     */
    @Test
    public void whenAddNewItemThenTrackerHasSomeItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    /**
     * Test for func "replace"
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item nextItem = new Item("test2", "testDescription2", 12345L);
        nextItem.setID(previous.getId());
        tracker.replace(previous.getId(), nextItem);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Test for func "delete"
     */
    @Test
    public void whenDeleteThenReturnNewArray() {
        Tracker tracker = new Tracker();
        Item[] expect = new Item[2];
        Item one = new Item("one", "oneDescription", 123L);
        tracker.add(one);
        expect[0] = one;
        Item two = new Item("two", "twoDescription", 124L);
        tracker.add(two);
        Item three = new Item("three", "threeDescription", 125L);
        tracker.add(three);
        expect[1] = three;
        tracker.delete(two.getId());
        System.out.println("123");
        assertThat(tracker.findAll(), is(expect));
    }

    /**
     * Test for func "find all"
     */
    @Test
    public void whenCreateArrayThenFindAll() {
        Tracker tracker = new Tracker();
        Item[] expect = new Item[3];
        Item one = new Item("one", "oneDescription", 123L);
        tracker.add(one);
        expect[0] = one;
        Item two = new Item("two", "twoDescription", 124L);
        tracker.add(two);
        expect[1] = two;
        Item three = new Item("three", "threeDescription", 125L);
        tracker.add(three);
        expect[2] = three;
        assertThat(tracker.findAll(), is(expect));
    }

    /**
     * Test for func "findByName"
     */
    @Test
    public void whenSendNameThenFindAll() {
        Tracker tracker = new Tracker();
        Item[] expect = new Item[2];
        Item one = new Item("one", "oneDescription", 123L);
        tracker.add(one);
        expect[0] = one;
        Item two = new Item("two", "twoDescription", 124L);
        tracker.add(two);
        Item three = new Item("one", "threeDescription", 125L);
        tracker.add(three);
        expect[1] = three;
        assertThat(tracker.findByName("one"), is(expect));
    }

    /**
     * Test for func "findById"
     */
    @Test
    public void whenSendIdThenFindOneObject() {
        Tracker tracker = new Tracker();
        Item one = new Item("one", "oneDescription", 123L);
        tracker.add(one);
        Item two = new Item("two", "twoDescription", 124L);
        tracker.add(two);
        Item three = new Item("one", "threeDescription", 125L);
        tracker.add(three);
        assertThat(tracker.findById(two.getId()), is(two));
    }
}
