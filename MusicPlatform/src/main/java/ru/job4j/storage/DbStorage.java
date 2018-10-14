package ru.job4j.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.util.PSQLException;
import ru.job4j.model.User;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DbStorage {

    // Обьединение трех таблиц
//    select * from
//    users as u inner join address as addr on u.id = addr.user_id
//    inner join result_types as rt on addr.user_id = rt.user_id
//    inner join music_type as mt on rt.type_id = mt.id;



    /**
     * Поля класса, содержащие:
     * 1) статическую ссылку на обьект класса BasicDataSource (пул коннектов)
     * 2) Синглтон данного класса
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();
    public static final DbStorage INSTANCE = new DbStorage();

    /**
     * Конструктор данного класса
     * Загружает драйвер psql
     * Задает значения в пулл коннектов
     * Создает таблицы юзеров и их привелегий, если таких еще нет и индексирует столб в созданной табце юзеров (id, логин, пароль и эмейл)
     * После этого создает 2 дефолтных права: админ и юзер
     */
    private DbStorage() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/music");
        SOURCE.setUsername("sergey");
        SOURCE.setPassword("");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try (Connection connection = SOURCE.getConnection(); Statement st = connection.createStatement()) {
            // Транзакция создания таблицы типов музыки
            connection.setAutoCommit(false);
            try {
                st.execute("create table music_type(id serial primary key, name varchar(100));");
                st.execute("insert into music_type (name) values ('rock');");
                st.execute("insert into music_type (name) values ('pop');");
                st.execute("insert into music_type (name) values ('country');");
                st.execute("insert into music_type (name) values ('metal');");
            } catch (PSQLException e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }

            // Транзакция создания таблицы адресов
            connection.setAutoCommit(false);
            try {
                st.execute("create table address (id serial primary key, user_id integer, name varchar(200));");
            } catch (PSQLException e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }

            // Транзакция создания таблицы прав пользователей
            connection.setAutoCommit(false);
            try {
                st.execute("create table role (id serial primary key, name varchar(200));");

                st.execute("insert into role (name) values ('user');");
                st.execute("insert into role (name) values ('moderator');");
                st.execute("insert into role (name) values ('admin');");
            } catch (PSQLException e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }

            st.execute("create table if not exists result_types(id serial primary key, user_id integer, type_id integer references music_type(id));");

            st.execute("create table if not exists users ("
                    + "id integer primary key, "
                    + "u_name varchar(200) not null, "
                    + "u_login varchar(200) not null, "
                    + "u_password varchar(200) not null, "
                    + "u_role integer references role(id), "
                    + "u_address integer references address(id), "
                    + "u_music_types integer references result_types(id));");

            st.execute("create index if not exists idIndex on users (id, u_login, u_password);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user, String address) {
        boolean result = false;

        try (Connection connection = SOURCE.getConnection()){
            connection.setAutoCommit(false);
            try {
                // Внесение данных во вспомогательную таблицу типов музыки
                PreparedStatement stOne = connection.prepareStatement(
                        "insert into result_types (user_id, type_id) values (?, ?);");
                stOne.setInt(1, user.getId());
                stOne.setInt(2, user.getMusicType());
                stOne.execute();

                // Внесение данных в таблицу адресов
                PreparedStatement adr = connection.prepareStatement(
                        "insert into address (user_id, name) values (?, ?) returning id;");
                adr.setInt(1, user.getId());
                adr.setString(2, address);
//                adr.executeUpdate();
                ResultSet generatedKeyForAddress = adr.executeQuery();
                int newAddress = 0;
                while (generatedKeyForAddress.next()) {
                    newAddress = generatedKeyForAddress.getInt("id");
                    System.out.println(newAddress);
                }

                // Внесение данных в таблицу пользователей
                PreparedStatement stTwo = connection.prepareStatement(
                        "insert into users (u_name, u_login, u_password, u_role, u_address, u_music_types, id) values (?, ?, ?, ?, ?, ?, ?);");
                stTwo.setString(1, user.getName());
                stTwo.setString(2, user.getLogin());
                stTwo.setString(3, user.getPassword());
                stTwo.setInt(4, user.getRole());
                stTwo.setInt(5, newAddress);
                stTwo.setInt(6, user.getMusicType());
                stTwo.setInt(7, user.getId());
                stTwo.execute();
                result = true;
            } catch (SQLException e) {
                result = false;
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Возращает обьект данного класса
     * @return
     */
    public static DbStorage getInstance() {
        return INSTANCE;
    }

    public Map<Integer, String> getAllRoles() {
        Map<Integer, String> result = new HashMap<>();
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement st = connection.prepareStatement("select * from role;");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.put(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<Integer, String> getAllTypes() {
        Map<Integer, String> result = new HashMap<>();
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement st = connection.prepareStatement("select * from music_type;");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.put(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
