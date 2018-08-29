package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для хранения данных о пользователях и их счетах
 * @author Galanov Sergey
 * @since 29.08.2018
 * @version 1.1
 */
@ThreadSafe
public class UserStorage {

    private Map<Integer, User> map = new HashMap<>();

    /**
     * Метод добавления пользователя
     * @param user
     * @return
     */
    public boolean add(User user) {
        boolean result;
        synchronized (this) {
            result = this.map.putIfAbsent(user.getId(), user) != null;
        }
        return result;
    }

    /**
     * Метод обновления информации о пользователе
     * @param user
     * @return
     */
    public boolean update(User user) {
        boolean result = false;
        synchronized (this) {
            if (this.map.containsKey(user.getId())) {
                this.map.get(user.getId()).setAmount(user.getAmount());
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод удаления пользователя
     * @param user
     * @return
     */
    public boolean delete(User user) {
        boolean result = false;
        if (this.map.containsKey(user.getId())) {
            this.map.remove(user.getId());
            result = true;
        }
        return result;
    }

    /**
     * Вспомогательный метод, который возращает текущую длину мапы
     */
    public int getSize() {
        int result;
        synchronized (this) {
            result = this.map.size();
        }
        return result;
    }

    /**
     * Метод перевода денег от одного пользователя к другому
     * сперва синхронизирует обьект с наибольшим id
     * затем синхронизирует обьект с наименьшим id
     * Затем меняет величину счета у каждого из обьектов
     * @param fromId - id пользователя, со счета которого требуется списать денег
     * @param toId - id пользователя, на счет которого требуется зачислить деньги
     * @param amount - величина переводимых денег
     */
    public void transfer(int fromId, int toId, int amount) {
        synchronized (this.map.get(fromId > toId ? fromId : toId)) {
            synchronized (this.map.get(fromId < toId ? fromId : toId)) {
                this.map.get(fromId).setAmount(this.map.get(fromId).getAmount() - amount);
                this.map.get(toId).setAmount(this.map.get(toId).getAmount() + amount);
            }
        }
    }

    public int getValue(int id) {
        int result;
        synchronized (this) {
            result = this.map.get(id).getAmount();
        }
        return result;
    }

}
