package ru.job4j.generics;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирования методов класса RoleStore и его родителя AbstractStore
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.0
 */
public class RoleStoreTest {

    /**
     * Тест добавления и поиска
     */
    @Test
    public void whenAddNewUserWhenCanFindHim() {
        RoleStore roleStore = new RoleStore();
        Role user1 = new Role("one");
        Role user2 = new Role("two");
        Role user3 = new Role("three");
        roleStore.add(user1);
        roleStore.add(user2);
        roleStore.add(user3);
        Role result = roleStore.findById("two");
        assertThat(result, is(user2));
    }

    /**
     * Тест добавления, замены и поиска элемента
     */
    @Test
    public void whenReplaceUserThenCanFindHim() {
        RoleStore roleStore = new RoleStore();
        Role user1 = new Role("one");
        Role user2 = new Role("two");
        Role user3 = new Role("three");
        roleStore.add(user1);
        roleStore.add(user2);
        roleStore.add(user3);
        Role newUser = new Role("two");
        roleStore.replace("two", newUser);
        Role result = roleStore.findById("two");
        assertThat(result, is(newUser));
    }

    /**
     * Тест удаления элемента
     */
    @Test
    public void whenDeleteUserThenCantFindHim() {
        RoleStore roleStore = new RoleStore();
        Role user1 = new Role("one");
        Role user2 = new Role("two");
        Role user3 = new Role("three");
        roleStore.add(user1);
        roleStore.add(user2);
        roleStore.add(user3);
        boolean isDeleted = roleStore.delete("two");
        assertThat(isDeleted, is(true));
        Role result = roleStore.findById("two");
        Role expect = null;
        assertThat(result, is(expect));
    }

}