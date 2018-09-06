package ru.job4j.tracker;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Class for working with List of items
 * @author Galanov Sergey
 * @since 06.09.2018
 * @version 1.7
 */
public class Tracker {

    /**
     * Приватные переменные:
     *  - ссылка на обьект класса Рандом (необходим для генерации id)
     *  - ссылка на обьект класса Properties, который содержит в себе пары ключ-значение из файла properties
     *  - Строки, хранящие url, имя пользователя и пароль
     */
    private static final Random RANDOM = new Random();
    private final Properties prop = new Properties();
    private String url;
    private String username;
    private String password;

    /**
     * Конструктор класса
     * Загружает все данные из файла properties
     * Создает подключение
     * Создает БД
     * Создает таблицу
     */
    public Tracker() throws ClassNotFoundException {
        Class cls = Class.forName("ru.job4j.tracker.Tracker");
        ClassLoader loader = cls.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("database.properties")) {
            this.prop.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String urlFirst = this.prop.getProperty("psql.connection_first");
        this.url = this.prop.getProperty("psql.connection_second");
        this.username = this.prop.getProperty("psql.username");
        this.password = this.prop.getProperty("psql.password");

        try (Connection connection = DriverManager.getConnection(urlFirst, username, password)) {
            this.createDatabase(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password)) {
            this.createTable(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Вспомогательный метод, создающий таблицу в Бд при условии, что ее там нет
     */
    private void createDatabase(Connection connection) {
        String createTable = this.prop.getProperty("create.database");
        try {
            Statement st = connection.createStatement();
            st.executeQuery(createTable);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private void createTable(Connection connection) {
        String createTable = this.prop.getProperty("create.table");
        try {
            Statement st = connection.createStatement();
            st.executeQuery(createTable);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * Метод для генерации рантомных id у пользователей
     * @return unique id (String format)
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
    }


    /**
     * Метод добавления в БД нового обьекта
     * Сперва получает из файла строку для запроса
     * Затем подготавливает запрос к БД, причем все ячейки добавляются безопасно
     * После этого выполняет запрос на Insert к БД
     * @param item - new item
     * @return - this item
     */
    public Item add(Item item) {
        item.setID(this.generateId());
        String add = this.prop.getProperty("psql.addItem");
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password)) {
            PreparedStatement st = connection.prepareStatement(add);
            st.setString(1, item.getId());
            st.setString(2, item.getName());
            st.setString(3, item.getDescription());
            st.setLong(4,item.getCreated());
            st.executeUpdate();
            //ResultSet rs = st.executeQuery(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод обновления данных в БД
     * @param id - id for finding Item
     * @param item - new Item for this id
     */
    public boolean replace(String id, Item item) {
        boolean isCorrect = false;
        String updateItem = this.prop.getProperty("psq.setItem");
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password)) {
            PreparedStatement ps = connection.prepareStatement(updateItem);
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setLong(3, item.getCreated());
            ps.setString(4, id);
            if (ps.executeUpdate() > 0) {
                isCorrect = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCorrect;
    }

    /**
     * Метод удаление из БД записи
     * @param id - id of deleting item
     * @return true if item is delete
     */
    public boolean delete(String id) {
        boolean isCorrect = false;
        String deleteItem = this.prop.getProperty("psql.deleteItem");
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password)) {
            PreparedStatement st = connection.prepareStatement(deleteItem);
            st.setString(1, id);
            if (st.executeUpdate() > 0) {
                isCorrect = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCorrect;
    }

    /**
     * Метод для возврата всех значений из БД
     * @return array of all none-null Items
     */

    public List<Item> findAll() {
        List<Item> result = new ArrayList<Item>();
        String getAllItems = this.prop.getProperty("psql.getAllItems");
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password)) {
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery(getAllItems);
            while (res.next()) {
                Item tmp = new Item(res.getString("name"),
                        res.getString("description"),
                        res.getLong("create_date"));
                tmp.setID(res.getString("id"));
                result.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод поиска записи в БД по полю имени
     * @param key - name (property)
     * @return array of Items with this name
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<Item>();
        String findByName = this.prop.getProperty("psql.getByName");
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password)) {
            PreparedStatement st = connection.prepareStatement(findByName);
            st.setString(1, key);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Item tmp = new Item(res.getString("name"),
                        res.getString("description"),
                        res.getLong("create_date"));
                tmp.setID(res.getString("id"));
                result.add(tmp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Функция по поиску обьекта в БД по id
     * @param id - unique id
     * @return Item with this unique id
     */
    public Item findById(String id) {
        Item result = null;
        String findById = this.prop.getProperty("psql.getById");
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password)) {
            PreparedStatement ps = connection.prepareCall(findById);
            ps.setString(1, id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                result = new Item(  res.getString("name"),
                                    res.getString("description"),
                                    res.getLong("create_date"));
                result.setID(res.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
