package ru.job4j.search;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для текста PriorityQuene.java
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.0
 */
public class PriorityQueneTest {

    /**
     * функция для тестирования добавления в коллекцию и ее обработку
     * В данном случае в коллекцию добаляется 5 элементов и сортируется
     * На выходе должен прийти обьект с наибольшим приоритетом
     */
    @Test
    public void whenAddFiveItemsThenFirstPriority() {
        PriorityQuene priorityQuene = new PriorityQuene();
        priorityQuene.putInTasks(new Task("Under High", 2));
        priorityQuene.putInTasks(new Task("High", 1));
        priorityQuene.putInTasks(new Task("Low", 5));
        priorityQuene.putInTasks(new Task("Upper Low", 4));
        priorityQuene.putInTasks(new Task("Medium", 3));
        priorityQuene.putInTasks(new Task("Super low", 8));
        Task result = priorityQuene.take();


        assertThat(result.getDescription(), is("High"));
    }
}
