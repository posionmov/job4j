package ru.job4j.generics;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестирования класса UserStore и методов его родителя AbstractStore
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.0
 */
public class UserStoreTest {

    /**
     * тест добавления и поиска
     */
    @Test
    public void whenAddNewUserWhenCanFindHim() {
        UserStore userStore = new UserStore();
        User user1 = new User("one");
        User user2 = new User("two");
        User user3 = new User("three");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        User result = userStore.findById("two");
        assertThat(result, is(user2));
    }

    /**
     * тест добавления, замены и поиска нового элемента
     */
    @Test
    public void whenReplaceUserThenCanFindHim() {
        UserStore userStore = new UserStore();
        User user1 = new User("one");
        User user2 = new User("two");
        User user3 = new User("three");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        User newUser = new User("two");
        userStore.replace("two", newUser);
        User result = userStore.findById("two");
        assertThat(result, is(newUser));
    }

    /**
     * Тест удаления элемента
     */
    @Test
    public void whenDeleteUserThenCantFindHim() {
        UserStore userStore = new UserStore();
        User user1 = new User("one");
        User user2 = new User("two");
        User user3 = new User("three");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        boolean isDeleted = userStore.delete("two");
        assertThat(isDeleted, is(true));
        User result = userStore.findById("two");
        User expect = null;
        assertThat(result, is(expect));
    }
}
