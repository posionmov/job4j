package ru.job4j.xml;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Date;

/**
 * Класс для создания таблицы в Базе данных
 * @author Galanov Sergey
 * @since 19.09.2018
 * @version 1.1
 */
public class StoreSQL {

    /**
     * Приватные поля класса
     * Содержат строки из файла property:
     *  - url
     *  - sql запрос на создание таблицы,
     *  - sql запрос на очистку талицы,
     *  - sql запрос на наполнение таблицы,
     *  - SQL запрос на получение всех данных из бд
     *
     * Так же содержит необходимые переменные для работы класса:
     *  - Обьект класса Date, который создается при создании обьекта. Необходим для отслеживания времени работы
     *  - Оставшееся время работы программы (в миллисекундах)
     */
    private String url;
    private String createTable, cleanTable, fillTable, getAll;
    private final Properties prop = new Properties();
    private int timeLeft;

    /**
     * Конструктор класса
     * Получает все значения из файла property для подключения к БД
     * Создает подключение к БД
     * @param str - название файла, который используется в качестве проперти (по умолчанию "sqllite.properties")
     */
    public StoreSQL(String str, int timeLeft) throws ClassNotFoundException {
        this.timeLeft = timeLeft;
        Class cls = Class.forName("ru.job4j.xml.StoreSQL");
        ClassLoader loader = cls.getClassLoader();
        try (InputStream io = loader.getResourceAsStream(str)) {
            this.prop.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Блок получения всех запросов из файла property и запись их в приватные поля
        this.url = this.prop.getProperty("sql.url");
        this.createTable = this.prop.getProperty("sql.createTable");
        this.cleanTable = this.prop.getProperty("sql.cleanTable");
        this.fillTable = this.prop.getProperty("sql.fillTable");
        this.getAll = this.prop.getProperty("sql.getAllData");
        this.creteAndClearTable();
    }

    /**
     * Метод подключения к БД, создания таблицы и очистки если она есть
     */
    private void creteAndClearTable() {
        try (Connection connection = DriverManager.getConnection(this.url)) {
            Date date = new Date();
            connection.setAutoCommit(false);
            if (connection != null) {
                Statement st = connection.createStatement();
                st.executeUpdate(this.createTable);
                st.executeUpdate(this.cleanTable);
                connection.commit();
                this.timeLeft = (int) (timeLeft - (new Date().getTime() - date.getTime()));
                System.out.println("Оставшееся время работы после создания  таблицы в БД - " + this.timeLeft);
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, создающий некоторое количество записей в БД
     * Если в таблице уже были записи, то удаляет их
     * Так же после каждой итекрации циклас проверяет время работы: если время бооьше 5 минут, то прерывает цикл
     * @param n - количество записей, которые необходимо создать в БД
     */
    public void generate(int n) {
        Date date = new Date();
        try (Connection connection = DriverManager.getConnection(this.url)) {
            if (connection != null) {
                connection.setAutoCommit(false);
                for (int i = 0; i < n; i++) {
                    PreparedStatement st = connection.prepareStatement(this.fillTable);
                    st.setInt(1, i + 1);
                    st.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.timeLeft = (int) (timeLeft - (new Date().getTime() - date.getTime()));
            System.out.println("Оставшееся время работы после наполнения таблицы в БД - " + this.timeLeft);
        }
    }

    /**
     * Метод, возращающий все значения из БД в виде коллекции на основе массива
     * @return коллекцию, содержащую все значения из БД
     */
    public List<Integer> getValuesFromBD() {
        List<Integer> result = new ArrayList<>();
        Date date = new Date();
        try (Connection connection = DriverManager.getConnection(this.url)) {
            if (connection != null) {
                Statement st = connection.createStatement();
                ResultSet res = st.executeQuery(this.getAll);
                while (res.next()) {
                    result.add(res.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.timeLeft = (int) (timeLeft - (new Date().getTime() - date.getTime()));
            System.out.println("Оставшееся время работы после заполнения массива записями из БД - " + this.timeLeft);
        }
        return result;
    }

    /**
     * Метод, возращающий оставшееся колличество миллисекунд
     * @return
     */
    public int getWorkTime() {
        return this.timeLeft;
    }
}
