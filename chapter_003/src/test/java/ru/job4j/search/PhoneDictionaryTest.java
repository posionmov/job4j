package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестирования PhoneDictionary.java
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.0
 */
public class PhoneDictionaryTest {

    /**
     * Текст на поиск если только одно совпадение
     */
    @Test
    public void whenAddNewPersonThenCanSearchHimFromKey() {
        PhoneDictionary dictionary = new PhoneDictionary();
        dictionary.addPersonInDictionary(new Person("Ivan", "Petrovich", "8-903-999-55-35", "Moscow"));
        dictionary.addPersonInDictionary(new Person("Sergey", "Ivanov", "8-912-123-33-29", "SPB"));
        List<Person> result = dictionary.findPersons("van");
        String expect = "Petrovich";
        assertThat(result.iterator().next().getSurname(), is(expect));
    }

    /**
     * Тест на поиск если несколько совпадений
     */
    @Test
    public void whenTwoMatchesThenFindTwoItems() {
        PhoneDictionary dictionary = new PhoneDictionary();
        dictionary.addPersonInDictionary(new Person("Ivan", "Petrovich", "8-903-999-55-35", "Moscow"));
        dictionary.addPersonInDictionary(new Person("Sergey", "Ivanov", "8-912-123-33-29", "SPB"));
        dictionary.addPersonInDictionary(new Person("Saha", "Alexandrow", "8-123-321-43-19", "SPB"));
        List<Person> result = dictionary.findPersons("van");
        int expect = 2;
        assertThat(result.size(), is(expect));
    }

    /**
     * Тест на поиск если нет совпадений
     */
    @Test
    public void whenNotMatchesThenSizeIsNull() {
        PhoneDictionary dictionary = new PhoneDictionary();
        dictionary.addPersonInDictionary(new Person("Ivan", "Petrovich", "8-903-999-55-35", "Moscow"));
        List<Person> result = dictionary.findPersons("bla");
        int expect = 0;
        assertThat(result.size(), is(expect));
    }
}
