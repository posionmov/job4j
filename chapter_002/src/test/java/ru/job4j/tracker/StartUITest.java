package ru.job4j.tracker;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing StartUI class
 * This tests imitate user
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public class StartUITest {

    /**
    * Test for add item in array
    */
    @Test
    public void whenUserAddNewItemThenTrackerHaveNewItemWithSameName() {
        Tracker tracker = new Tracker();
        ConsoleInput input = new StubInput(new String[] {"0", "test name", "test desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    /**
     * Test for find all items in array
     */
    @Test
    public void whenUserAddTwoNewItemsThenTrackerHaveTwoItems() {
        Tracker tracker = new Tracker();
        ConsoleInput input = new StubInput(new String[] {"0", "name1", "desc1", "0", "name2", "desc2", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Test for edit some item in array
     */
    @Test
    public void whenUpdateItemThenTrackerHaveUpdatedItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("name1", "desc1", System.nanoTime());
        tracker.add(item);
        ConsoleInput input = new StubInput(new String[] {"2", item.getId(), "name2", "desc2", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("name2"));
    }

    /**
     * Test for deleting item in array
     */
    @Test
    public void whenDeleteItemThenTrackerDontHaveThisItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("name1", "desc1", System.nanoTime());
        tracker.add(item);
        ConsoleInput input = new StubInput(new String[] {"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(0));
    }

    /**
     * Test for func finding item by id
     */
    @Test
    public void whenAddItemThenTrackerCanFindByID() {
        Tracker tracker = new Tracker();
        Item item = new Item("name1", "desc1", System.nanoTime());
        tracker.add(item);
        Item secondItem = new Item("name2", "desc2", System.nanoTime());
        tracker.add(secondItem);
        ConsoleInput input = new StubInput(new String[] {"4", secondItem.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(secondItem.getId()).getId(), is(secondItem.getId()));
    }

    /**
     * Test for func finding all items by name
     */
    @Test
    public void whenAddingTwoItemsWithSameNameThenTrackerReturnTwoItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("name1", "desc1", System.nanoTime());
        tracker.add(item);
        Item secondItem = new Item("name1", "desc2", System.nanoTime());
        tracker.add(secondItem);
        ConsoleInput input = new StubInput(new String[] {"5", item.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName(item.getName()).length, is(2));
    }
}
