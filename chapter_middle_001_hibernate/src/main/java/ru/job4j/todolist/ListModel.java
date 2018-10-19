package ru.job4j.todolist;

import java.sql.Timestamp;

/**
 * Класс, описывающий модель записейв БД
 * @author Galanov Sergey
 * @since 19.10.2018
 * @version 1.0
 */
public class ListModel {

    /**
     * Приватные поля класса
     * Содержат:
     *      id
     *      описание
     *      дата создания
     *      завершенность (boolean)
     */
    private int id;
    private String desc;
    private Timestamp createDate;
    private boolean done;

    /**
     * Блок геттеров и сеттеров
     * @return
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
