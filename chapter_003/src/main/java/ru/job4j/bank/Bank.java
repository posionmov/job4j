package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, реализующий работу банка
 * @author Galanov Sergey
 * @since 08.08.2018
 * @version 1.1
 */
public class Bank {

    private Map<User, List<Account>> customers = new HashMap<>();

    /**
     * Функция по добавлению пользователя в мапу
     * @param user - пользователь
     */
    public void addUser(User user) {
        this.customers.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Функция по удалению пользователя из мапы
     * @param user - пользователь
     */
    public void deleteUser(User user) {
        this.customers.remove(user);
    }

    /**
     * Функция по добавлению счета пользователю
     * @param passport - паспортные данные
     * @param account - Акаунт пользователя
     */
    //добавить счёт пользователю.
    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> item : this.customers.entrySet()) {
            if (item.getKey().getPassport().equals(passport)) {
                item.getValue().add(account);
                break;
            }
        }
    }

    /**
     * Функция по удалению счета у пользоателя
     * @param passport - паспортные данные пользователя
     * @param account - аккаунт пользователя
     */
    //удалить один счёт пользователя
    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> item : this.customers.entrySet()) {
            if (item.getKey().getPassport().equals(passport)) {

                for (Account curAccount : item.getValue()) {
                    if (curAccount.equals(account)) {
                        this.customers.get(item.getKey()).remove(account);
                    }
                }

            }
        }
    }

    /**
     * Функция, возращающая все счета у пользователя
     * @param passport - паспортные данные пользователя
     * @return - коллекцию счетов пользователя
     */
    // Получить список всех счетов для пользователя
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        for (Map.Entry<User, List<Account>> item : this.customers.entrySet()) {
            if (item.getKey().getPassport().equals(passport)) {
                result.addAll(item.getValue());
                break;
            }
        }
        return result;
    }

    /**
     * Функция по переброске денег с одного счета на другой
     * При этом если денег у первого пользователя не хватает, то ничего не происходит и просто возращается false
     * @param srcPassport - паспортные данные плательщика
     * @param srcRequisite - реквизиты счета плательщика
     * @param destPassport - паспортные данные получателя
     * @param dstRequisite - реквизиты счета получателя
     * @param amount - отдаваемая сумма
     * @return - успех проделанной операции (true - удачно, false - не получилось)
     */
    public boolean transferMoney(String srcPassport, int srcRequisite, String destPassport, int dstRequisite, double amount) {
        boolean isEnough = false;
        Account srcAccount = this.getAccount(srcRequisite, srcPassport);
        Account destAccount = this.getAccount(dstRequisite, destPassport);
        if (srcAccount.getRequisites() != 0 && srcAccount.getValue() >= amount) {
            isEnough = true;
            srcAccount.decreaseValue(amount);
            destAccount.increaseValue(amount);
        }
        return isEnough;
    }

    /**
     * Опциональный метод, возращающий список всех пользователей
     * @return коллекция пользователей
     */
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        for (Map.Entry<User, List<Account>> item : this.customers.entrySet()) {
            result.add(item.getKey());
            System.out.println(item.getKey().getPassport());
        }
        return result;
    }

    /**
     * Метод полуения счета по реквизитам счета и паспортным данным
     * @param requisites - реквизиты счета
     * @param passport - паспортные данные
     * @return конекретный счет конкретного пользователя
     */
    public Account getAccount(int requisites, String passport) {
        Account result = new Account(0, 0);
        List<Account> list = this.getUserAccounts(passport);
        for (Account account : list) {
            if (account.getRequisites() == requisites) {
                result = account;
                break;
            }
        }
        return result;
    }

}
