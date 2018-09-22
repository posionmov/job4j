package ru.job4j.users;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.crud.Store;
import ru.job4j.crud.User;
import java.sql.*;
import java.util.HashMap;

/**
 * Класс, реализующий работу с пулом коннектов
 * Многопоточный синглтон
 * @author Galanov Sergey
 * @since 22.09.2018
 * @version 1.0
 */
public class DbStore implements Store {

    /**
     * Поля класса, содержащие:
     * 1) статическую ссылку на обьект класса BasicDataSource (пул коннектов)
     * 2) Синглтон данного класса
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();
    public static final DbStore INSTANCE = new DbStore();

    /**
     * Конструктор данного класса
     * Загружает драйвер psql
     * Задает значения в пулл коннектов
     * Создает таблицу если такой еще нет и индексирует столб в созданной таблице с именем id
     */
    private DbStore() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/users");
        SOURCE.setUsername("sergey");
        SOURCE.setPassword("");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try (Connection connection = SOURCE.getConnection(); Statement st = connection.createStatement()) {
            st.execute("create table if not exists users (id integer primary key, u_name varchar(200), u_login varchar(200), u_email varchar(200), u_create_date varchar(200));");
            st.execute("create index idIndex on users(id)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возращает обьект данного класса
     * @return
     */
    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Метод добавления пользователя в БД
     * @param model
     * @return
     */
    @Override
    public boolean add(User model) {
        boolean result = true;
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("insert into users (id, u_name, u_login, u_email, u_create_date) values (?, ?, ?, ?, ?);")) {
            st.setInt(1, model.getId());
            st.setString(2, model.getName());
            st.setString(3, model.getLogin());
            st.setString(4, model.getEmail());
            st.setString(5, model.getCreateDate());
            st.executeUpdate();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод обновления данных о пользователе в БД
     * @param idOldUser
     * @param user
     * @return
     */
    @Override
    public boolean update(int idOldUser, User user) {
        boolean result = true;
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("update users set u_name = ?, u_login = ?, u_email = ?, u_create_date = ? where id = ?;")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getCreateDate());
            st.setInt(5, idOldUser);
            st.executeQuery();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод удаления пользователя из БД
     * @param userId
     * @return
     */
    @Override
    public boolean delete(int userId) {
        boolean result = true;
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("delete from users where id = ?;")) {
            st.setInt(1, userId);
            st.executeQuery();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод полученя всех польователей из БД
     * @return
     */
    @Override
    public HashMap<Integer, User> findAll() {
        HashMap<Integer, User> users = new HashMap<>();

        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("select * from users;")) {
            ResultSet rset = st.executeQuery();
            while (rset.next()) {
                User user = new User(rset.getString("u_name"), rset.getString("u_login"), rset.getString("u_email"));
                user.setId(rset.getInt("id"));
                user.setCreateDate(rset.getString("u_create_date"));
                users.put(user.getId(), user);
            }
            rset.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Метод получения пользователя по id из БД
     * @param id
     * @return
     */
    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("select * from users where id = ?;")) {
            st.setInt(1, id);
            ResultSet rset = st.executeQuery();
            while (rset.next()) {
                user = new User(rset.getString("u_name"), rset.getString("u_login"), rset.getString("u_email"));
                user.setId(rset.getInt("id"));
                user.setCreateDate(rset.getString("u_create_date"));
            }
            rset.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

