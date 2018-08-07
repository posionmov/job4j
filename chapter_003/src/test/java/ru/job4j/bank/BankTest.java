package ru.job4j.bank;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестирования Bank.java
 * @author Galanov Sergey
 * @since 07.08.2018
 * @version 1.0
 */
public class BankTest {

    /**
     * Тест на добавление пользователя, ему счет и поиск всех счетов пользователя
     */
    @Test
    public void whenAddNewItemThenCanFindThey() {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "555 444"));
        bank.addAccountToUser("555 444", new Account(500, 11111111));
        bank.addUser(new User("Alex", "333 111"));
        bank.addAccountToUser("333 111", new Account(300, 22222222));
        List<Account> result = new ArrayList<>();
        result.add(new Account(300, 22222222));
        assertThat(bank.getUserAccounts("333 111").get(0).getValue(), is(result.get(0).getValue()));
        assertThat(bank.getUserAccounts("333 111").get(0).getRequisites(), is(result.get(0).getRequisites()));
    }

    /**
     * Тест на удаление пользователя и одного счета другого пользователя
     */
    @Test
    public void whenAddThreeAndDeleteSomeElementsThenCorrectDelete() {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "111 222"));
        bank.addAccountToUser("111 222", new Account(500, 11111111));
        User userForDelete = new User("Alex", "222 333");
        bank.addUser(userForDelete);
        bank.addAccountToUser("222 333", new Account(300, 22222222));
        Account accountForDelete = new Account(700, 33333333);
        bank.addUser(new User("Michail", "333 444"));
        bank.addAccountToUser("333 444", accountForDelete);
        bank.addAccountToUser("333 444", new Account(10, 44444444));
        bank.deleteUser(userForDelete);
        bank.deleteAccountFromUser("333 444", accountForDelete);
        assertThat(bank.getUserAccounts("333 444").size(), is(1)); // Проверка что у пользователя удалился один из счетов
        assertThat(bank.getAllUsers().size(), is(2)); // Проверка что один из пользователей удалился
    }

    /**
     * Тест на отправку с одного счета на другой
     */
    @Test
    public void whenTransferMoneyThenCorrectResult() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "111 222");
        User user2 = new User("Michail", "222 333");
        Account account1 = new Account(500, 11111111);
        Account account2 = new Account(100, 22222222);
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser("111 222", account1);
        bank.addAccountToUser("222 333", account2);
        boolean result1 = bank.transferMoney("111 222", 11111111, "222 333", 22222222, 100);
        boolean result2 = bank.transferMoney("222 333", 22222222, "111 222", 11111111, 200);
        assertThat(result1, is(true));
        assertThat(result2, is(false));
    }

}
