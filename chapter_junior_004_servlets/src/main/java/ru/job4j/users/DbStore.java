package ru.job4j.users;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.crud.Store;
import ru.job4j.crud.User;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, реализующий работу с пулом коннектов
 * Многопоточный синглтон
 * @author Galanov Sergey
 * @since 26.09.2018
 * @version 1.3
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
     * Создает таблицы юзеров и их привелегий, если таких еще нет и индексирует столб в созданной табце юзеров (id, логин, пароль и эмейл)
     * После этого создает 2 дефолтных права: админ и юзер
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
            st.execute("create table if not exists user_rights (id serial primary key, rights varchar(200));");
            st.execute("create table if not exists users (id integer primary key, u_name varchar(200) not null, u_login varchar(200) not null, u_password varchar(200) not null, u_email varchar(200) not null, u_create_date varchar(200) not null, u_password varchar(200) not null, u_right integer references user_rights(id));");
            st.execute("create index idIndex on users(id, u_login, u_password, u_email)");
            st.execute("insert into user_rights (rights) values ('admin');");
            st.execute("insert into user_rights (rights) values ('user');");
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
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("insert into users (id, u_name, u_login, u_email, u_create_date, u_right, u_password) values (?, ?, ?, ?, ?, ?, ?);")) {
            st.setInt(1, model.getId());
            st.setString(2, model.getName());
            st.setString(3, model.getLogin());
            st.setString(4, model.getEmail());
            st.setString(5, model.getCreateDate());
            st.setInt(6, model.getRight());
            st.setString(7, model.getPassword());
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
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("update users set u_name = ?, u_login = ?, u_email = ?, u_create_date = ?, u_password = ?, u_right = ?  where id = ?;")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getCreateDate());
            st.setString(5, user.getPassword());
            st.setInt(6, user.getRight());
            st.setInt(7, idOldUser);
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
                User user = new User(rset.getString("u_name"), rset.getString("u_login"), rset.getString("u_email"), rset.getInt("u_right"), rset.getString("u_password"));
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
                user = new User(rset.getString("u_name"), rset.getString("u_login"), rset.getString("u_email"), rset.getInt("u_right"), rset.getString("u_password"));
                user.setId(rset.getInt("id"));
                user.setCreateDate(rset.getString("u_create_date"));
            }
            rset.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Метод проверки вводимого логина и пароля
     * Если такой записи в БД нет, то вернется false
     * @param userName - логин от пользователя
     * @param userPassword - пароль от пользователя
     * @return true если такой пользователь есть
     */
    @Override
    public boolean checkCorrect(String userName, String userPassword) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("select * from users where u_name = ? and u_password = ?;")) {
            st.setString(1, userName);
            st.setString(2, userPassword);
            ResultSet rset = st.executeQuery();
            if (rset.next()) {
                result = true;
            }
            rset.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод, возращающий карту всех прав пользователей (необходимый для регистрации)
     * @return - карта прав пользователей
     */
    @Override
    public Map<Integer, String> getAllRights() {
        Map<Integer, String> result = new HashMap<>();
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("select * from user_rights;")) {
            ResultSet rset = st.executeQuery();
            while (rset.next()) {
                result.put(rset.getInt(1), rset.getString(2));
            }
            rset.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод проверки ввода логина и пароля
     * Если полученный логин и пароль существуют, то возращает обьект класса User
     * @param userName
     * @param userPassword
     * @return null если пользоваеля с таким логином и паролем не существует
     */
    public User checkUser(String userName, String userPassword) {
        User result = null;
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("select * from users where u_name = ? and u_password = ?;")) {
            st.setString(1, userName);
            st.setString(2, userPassword);
            ResultSet rset = st.executeQuery();
            if (rset.next()) {
                result = new User(rset.getString("u_name"), rset.getString("u_login"), rset.getString("u_email"), rset.getInt("u_right"), rset.getString("u_password"));
            }
            rset.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

