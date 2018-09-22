//package ru.job4j.tracker;
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.After;
//import static org.junit.Assert.*;
//import static org.hamcrest.core.Is.is;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
///**
// * Class for testing StartUI class
// * This tests imitate user
// * @author Galanov Sergey
// * @since 06.08.2018
// * @version 1.3
// */
//public class StartUITest {
//    /**
//     * Содержит дефолтный вывод в консоль и Буфер (для результата)
//     */
//    private final PrintStream stdout = System.out;
//    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
//    private String menu = new StringBuilder().append("0 - Создание заявки").append(System.lineSeparator())
//            .append("1 - Показать все заявки").append(System.lineSeparator())
//            .append("2 - Редактирование заявки").append(System.lineSeparator())
//            .append("3 - Удаление заявки").append(System.lineSeparator())
//            .append("4 - Поиск заявки по id").append(System.lineSeparator())
//            .append("5 - Поиск заявки по имени").append(System.lineSeparator())
//            .append("6 - Выйти из программы").append(System.lineSeparator())
//            .append("----------------------------------------------------------").append(System.lineSeparator()).toString();
//
//    /**
//     * Methods before test is begin
//     */
//    @Before
//    public void loadOutput() {
//        System.out.println("execute before method");
//        System.setOut(new PrintStream(this.out));
//    }
//
//    /**
//     * Methods after test is begin
//     */
//    @After
//    public void backOutput() {
//        System.setOut(this.stdout);
//        System.out.println("execute after method");
//    }
//
//
//    /**
//    * Test for add item in array
//    */
//    @Test
//    public void whenUserAddNewItemThenTrackerHaveNewItemWithSameName() throws ClassNotFoundException {
//        Tracker tracker = new Tracker();
//        ValidateInput input = new ValidateInput(new StubInput(new String[] {"0", "test name", "test desc", "6"}));
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findAll().get(0).getName(), is("test name"));
//    }
//
//    /**
//     * Test for find all items in array
//     */
//    @Test
//    public void whenUserAddTwoNewItemsThenTrackerHaveTwoItems() throws ClassNotFoundException {
//        Tracker tracker = new Tracker();
//        Item item1 = new Item("name1", "desc1", System.nanoTime());
//        Item item2 = new Item("name2", "desc2", System.nanoTime());
//        tracker.add(item1);
//        tracker.add(item2);
//        ValidateInput input = new ValidateInput(new StubInput(new String[] {"1", "6"}));
//        new StartUI(input, tracker).init();
//        assertThat(new String(out.toByteArray()), is(new StringBuilder()
//                .append(this.menu)
//                .append("<-------------- Список всех текущих заявок -------------->").append(System.lineSeparator())
//                .append("Id: ").append(item1.getId()).append(", Имя: name1, Описание: desc1").append(System.lineSeparator())
//                .append("Id: ").append(item2.getId()).append(", Имя: name2, Описание: desc2").append(System.lineSeparator())
//                .append("<--------- Окончание списока всех текущих заявок --------->").append(System.lineSeparator())
//                .append(this.menu)
//                .append("Выключение").append(System.lineSeparator()).toString()));
//        System.setOut(stdout);
//    }
//
//    /**
//     * Test for edit some item in array
//     */
//    @Test
//    public void whenUpdateItemThenTrackerHaveUpdatedItem() throws ClassNotFoundException {
//        Tracker tracker = new Tracker();
//        Item item = new Item("name1", "desc1", System.nanoTime());
//        tracker.add(item);
//        ValidateInput input = new ValidateInput(new StubInput(new String[] {"2", item.getId(), "name2", "desc2", "6"}));
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findAll().get(0).getName(), is("name2"));
//    }
//
//    /**
//     * Test for deleting item in array
//     */
//    @Test
//    public void whenDeleteItemThenTrackerDontHaveThisItem() throws ClassNotFoundException {
//        Tracker tracker = new Tracker();
//        Item item = new Item("name1", "desc1", System.nanoTime());
//        tracker.add(item);
//        ValidateInput input = new ValidateInput(new StubInput(new String[] {"3", item.getId(), "6"}));
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findAll().size(), is(0));
//    }
//
//    /**
//     * Test for func finding item by id
//     */
//    @Test
//    public void whenAddItemThenTrackerCanFindByID() throws ClassNotFoundException {
//        Tracker tracker = new Tracker();
//        Item item1 = new Item("name1", "desc1", System.nanoTime());
//        Item item2 = new Item("name2", "desc2", System.nanoTime());
//        tracker.add(item1);
//        tracker.add(item2);
//        ValidateInput input = new ValidateInput(new StubInput(new String[] {"4", item1.getId(), "6"}));
//        new StartUI(input, tracker).init();
//        assertThat(new String(out.toByteArray()), is(new StringBuilder()
//                .append(this.menu)
//                .append("<-------------- Информация о заявке -------------->").append(System.lineSeparator())
//                .append("ID: ").append(item1.getId()).append(System.lineSeparator())
//                .append("Имя: ").append(item1.getName()).append(System.lineSeparator())
//                .append("Описание: ").append(item1.getDescription()).append(System.lineSeparator())
//                .append("Дата создания: ").append(item1.getCreated()).append(System.lineSeparator())
//                .append("<-------------- Информация о заявке --------------->").append(System.lineSeparator())
//                .append(this.menu)
//                .append("Выключение").append(System.lineSeparator()).toString()));
//        System.setOut(stdout);
//    }
//
//    /**
//     * Test for func finding all items by name
//     */
//    @Test
//    public void whenAddingTwoItemsWithSameNameThenTrackerReturnTwoItems() throws ClassNotFoundException {
//        Tracker tracker = new Tracker();
//        Item item1 = new Item("name1", "desc1", System.nanoTime());
//        Item item2 = new Item("name2", "desc2", System.nanoTime());
//        Item item3 = new Item("name1", "desc3", System.nanoTime());
//        tracker.add(item1);
//        tracker.add(item2);
//        tracker.add(item3);
//        ValidateInput input = new ValidateInput(new StubInput(new String[] {"5", item1.getName(), "6"}));
//        new StartUI(input, tracker).init();
//        assertThat(new String(out.toByteArray()), is(new StringBuilder()
//                .append(this.menu)
//                .append("Найденные заявки:").append(System.lineSeparator())
//                .append("<------------- Информация о заявке № 1----------->").append(System.lineSeparator())
//                .append("ID: ").append(item1.getId()).append(System.lineSeparator())
//                .append("Имя: ").append(item1.getName()).append(System.lineSeparator())
//                .append("Описание: ").append(item1.getDescription()).append(System.lineSeparator())
//                .append("Дата создания: ").append(item1.getCreated()).append(System.lineSeparator())
//                .append("<-------------- Информация о заявке ------------->").append(System.lineSeparator())
//                .append("<------------- Информация о заявке № 2----------->").append(System.lineSeparator())
//                .append("ID: ").append(item3.getId()).append(System.lineSeparator())
//                .append("Имя: ").append(item3.getName()).append(System.lineSeparator())
//                .append("Описание: ").append(item3.getDescription()).append(System.lineSeparator())
//                .append("Дата создания: ").append(item3.getCreated()).append(System.lineSeparator())
//                .append("<-------------- Информация о заявке ------------->").append(System.lineSeparator())
//                .append("<--------------- Конец найденных заявок --------------->").append(System.lineSeparator())
//                .append(this.menu)
//                .append("Выключение").append(System.lineSeparator()).toString()));
//        System.setOut(stdout);
//    }
//}
