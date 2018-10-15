package ru.job4j.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.util.PSQLException;
import ru.job4j.model.User;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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




            //Транзакция создания таблиц улиц, городов, стран
            try  {
                connection.setAutoCommit(false);
                // Таблица стран
                st.execute("create table countries(id serial primary key, country_name varchar(200));");
                st.execute("insert into countries (country_name) values ('Россия');"); //1
                st.execute("insert into countries (country_name) values ('Украина');"); //2
                st.execute("insert into countries (country_name) values ('Канада');"); //3

                // Таблица городов
                st.execute("create table cities (id serial primary key, city_name varchar(200), country_id integer references countries(id));");
                st.execute("insert into cities (city_name, country_id) values ('Москва', 1);");             //1
                st.execute("insert into cities (city_name, country_id) values ('Санкт-Питербург', 1);");    //2
                st.execute("insert into cities (city_name, country_id) values ('Ярославль', 1);");          //3

                st.execute("insert into cities (city_name, country_id) values ('Киев', 2);");               //4
                st.execute("insert into cities (city_name, country_id) values ('Львов', 2);");              //5
                st.execute("insert into cities (city_name, country_id) values ('Одесса', 2);");             //6

                st.execute("insert into cities (city_name, country_id) values ('Торонто', 3);");            //7
                st.execute("insert into cities (city_name, country_id) values ('Оттава', 3);");             //8
                st.execute("insert into cities (city_name, country_id) values ('Монреаль', 3);");           //9

                // Таблица улиц
                st.execute("create table streets (id serial primary key, street_name varchar(200), city_id integer references cities(id));");
                st.execute("insert into streets (street_name, city_id) values ('Смоленская улица', 1);");
                st.execute("insert into streets (street_name, city_id) values ('улица Арбат', 1);");
                st.execute("insert into streets (street_name, city_id) values ('улица Красная Пресня', 1);");

                st.execute("insert into streets (street_name, city_id) values ('Адмиралтейский проспект', 2);");
                st.execute("insert into streets (street_name, city_id) values ('Амбарная улица', 2);");
                st.execute("insert into streets (street_name, city_id) values ('Артиллерийский переулок', 2);");

                st.execute("insert into streets (street_name, city_id) values ('улица Ветеранов', 3);");
                st.execute("insert into streets (street_name, city_id) values ('Вольная улица', 3);");
                st.execute("insert into streets (street_name, city_id) values ('улица Выставочная', 3);");

                st.execute("insert into streets (street_name, city_id) values ('Автозаводская улица ', 4);");
                st.execute("insert into streets (street_name, city_id) values ('Александровская улица ', 4);");
                st.execute("insert into streets (street_name, city_id) values ('Астраханская улица ', 4);");

                st.execute("insert into streets (street_name, city_id) values ('Площадь Соборная ', 5);");
                st.execute("insert into streets (street_name, city_id) values ('улица Подвальная', 5);");
                st.execute("insert into streets (street_name, city_id) values ('улица Григоровича', 5);");

                st.execute("insert into streets (street_name, city_id) values ('Абрикосовая улица', 6);");
                st.execute("insert into streets (street_name, city_id) values ('Авиационная улица', 6);");
                st.execute("insert into streets (street_name, city_id) values ('Авангардная улица', 6);");

                st.execute("insert into streets (street_name, city_id) values ('улица Колледж-стрит', 7);");
                st.execute("insert into streets (street_name, city_id) values ('улица Yonge Street', 7);");
                st.execute("insert into streets (street_name, city_id) values ('улица Бэй-стрит', 7);");

                st.execute("insert into streets (street_name, city_id) values ('Albert Street', 8);");
                st.execute("insert into streets (street_name, city_id) values ('Booth Street', 8);");
                st.execute("insert into streets (street_name, city_id) values ('O-Connor Street', 8);");

                st.execute("insert into streets (street_name, city_id) values ('Кресент-стрит', 9);");
                st.execute("insert into streets (street_name, city_id) values ('улица Ле-Руайе', 9);");
                st.execute("insert into streets (street_name, city_id) values ('улица Сент-Дени', 9);");

            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }



            // Транзакция создания таблицы адресов
            connection.setAutoCommit(false);
            try {
                st.execute(
                        "create table address (id serial primary key, user_id integer, country_id integer references countries(id), city_id integer references cities(id), street_id integer references streets(id));");
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

    public Map<Integer, String> getAllCountries() {
        Map<Integer, String> result = new HashMap<>();

        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("select * from countries;")) {
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result.putIfAbsent(rs.getInt("id"), rs.getString("country_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<Integer, String> getAllCitiesForCountry(int countryId)  {
        Map<Integer, String> result = new HashMap<>();

        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("select * from cities where country_id = ?;")) {
            st.setInt(1, countryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.putIfAbsent(rs.getInt("id"), rs.getString("country_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<Integer, String> getAllStreetsForCity(int cityId)  {
        Map<Integer, String> result = new HashMap<>();
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("select * from streets where city_id = ?;")) {
            st.setInt(1, cityId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.putIfAbsent(rs.getInt("id"), rs.getString("country_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


//    public void addNewCityToCountry()



    public Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> getAllAddresses() {

        Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> result = new HashMap<>();
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement(
                "select * from streets as str inner join cities as ci on str.city_id=ci.id inner join countries as cou on ci.country_id=cou.id;")) {

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                if (result.get(rs.getInt(7)) == null) {

                    Map<Integer, String> city = new HashMap<>();
                    Map<String, Map<Integer, String>> cityName = new HashMap<>();
                    Map<Integer, Map<String, Map<Integer, String>>> cityIndex = new HashMap<>();
                    Map<String, Map<Integer, Map<String, Map<Integer, String>>>> countryName = new HashMap<>();

                    city.putIfAbsent(rs.getInt(1), rs.getString(2));
                    cityName.putIfAbsent(rs.getString(5), city);
                    cityIndex.putIfAbsent(rs.getInt(4), cityName);
                    countryName.putIfAbsent(rs.getString(8), cityIndex);
                    result.putIfAbsent(rs.getInt(7), countryName);
                } else {
                    Map<String, Map<Integer, Map<String, Map<Integer, String>>>> countryName = result.get(rs.getInt(7));
                    Map<Integer, Map<String, Map<Integer, String>>> cityIndex = null;
                    Map<String, Map<Integer, String>> cityName = null;
                    Map<Integer, String> city = null;

                    if (countryName.get(rs.getString(8)) == null) {
                        cityIndex = new HashMap<>();
                    } else {
                        cityIndex = countryName.get(rs.getString(8));
                        if (cityIndex.get(rs.getInt(4)) == null) {
                            cityName = new HashMap<>();
                        } else {
                            cityName = cityIndex.get(rs.getInt(4));
                            if (cityName.get(rs.getInt(1)) == null) {
                                city = new HashMap<>();
                            } else {
                                city = cityName.get(rs.getInt(1));
                            }
                        }
                    }

                    city.put(rs.getInt(1), rs.getString(2));
                    cityName.putIfAbsent(rs.getString(5), city);
                    cityIndex.putIfAbsent(rs.getInt(4), cityName);
                    countryName.putIfAbsent(rs.getString(8), cityIndex);

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        new DbStorage().getAllAddresses();
//        Map<Integer, String> ex = new HashMap<>();
////        System.out.println(ex.putIfAbsent(1, "one")); // Возращает null если такого обьекта еще небыло
////        System.out.println(ex.putIfAbsent(1, "one"));
////        System.out.println(ex.putIfAbsent(1, "one"));
////        System.out.println(ex.putIfAbsent(1, "one"));
////        System.out.println(ex.get(2)); // ВОзращает null если такого обьекта нету
//        Map<Integer, Map<Integer, String>> example = new HashMap<>();
//        Map<Integer, String> test = new HashMap<>();
//        example.put(1, test);
//
//        Map<Integer, String> res = example.get(1);
//        res.put(2, "test2");
//
//        System.out.println(test.size());

//        Map<Integer, Map<Integer, String>> testMap = new HashMap<>();
//        Map<Integer, String> mapInMap = new HashMap<>();
//        mapInMap.put(123, "testtest");
//        testMap.put(1, mapInMap);
//
//        Map<Integer, String> newMapInMap = testMap.get(1);
//        newMapInMap.put(444, "qweasd");
////        testMap.replace(1, newMapInMap);
//        for (Map.Entry<Integer, Map<Integer, String>> entry : testMap.entrySet()) {
//            for (Map.Entry<Integer, String> entryOne : entry.getValue().entrySet())  {
//                System.out.println(entryOne.getValue());
//            }
//        }
    }
}