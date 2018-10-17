package ru.job4j.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.util.PSQLException;
import ru.job4j.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, обуспечивающий подключение к БД и получение из нее данных
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public class DbStorage implements Store {
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
                st.execute("insert into music_type (name) values ('jazz');");
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
            st.execute("create index if not exists typeIdIndex on result_types (user_id, type_id);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод добавления пользователя
     * @param user
     * @return
     */
    public boolean addUser(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try {
                fillMusicTypes(user, connection);
                PreparedStatement adr = connection.prepareStatement("insert into address (user_id, country_id, city_id, street_id) values (?, ?, ?, ?) returning id;");
                adr.setInt(1, user.getId());
                adr.setInt(2, user.getAddress().get(0));
                adr.setInt(3, user.getAddress().get(1));
                adr.setInt(4, user.getAddress().get(2));
                ResultSet generatedKeyForAddress = adr.executeQuery();
                int newAddress = 0;
                while (generatedKeyForAddress.next()) {
                    newAddress = generatedKeyForAddress.getInt("id");
                }
                // Внесение данных в таблицу пользователей
                PreparedStatement stTwo = connection.prepareStatement(
                        "insert into users (u_name, u_login, u_password, u_role, u_address, id) values (?, ?, ?, ?, ?, ?);");
                stTwo.setString(1, user.getName());
                stTwo.setString(2, user.getLogin());
                stTwo.setString(3, user.getPassword());
                stTwo.setInt(4, user.getRole());
                stTwo.setInt(5, newAddress);
                stTwo.setInt(6, user.getId());
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
     * Вспомогательный метод, производящий поиск всех типов музыки для пользователя в БД
     * @param userId - id пользователя, для которого необходимо искать типы музыки
     * @return
     */
    private List<Integer> getAllMusicTypesForUser(int userId) {
        List<Integer> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement st = connection.prepareStatement("select * from result_types where user_id = ?;");
            st.setInt(1, userId);
            ResultSet musicTypes = st.executeQuery();
            while (musicTypes.next()) {
                result.add(musicTypes.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод получения всех прав доступа
     * @return ассоциативная коллекция ролей,
     *      ключ - id права доступа
     *      значение - название права доступа
     */
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

    /**
     * Метод получения всех типов музыки
     * @return ассоциативная коллекция ролей,
     *      ключ - id типа музыки
     *      значение - название типа музыки
     */
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

    /**
     * Метод получения всех адресов
     * @return ассоциативная коллекция, в которой хранятся все страны, принадлежащие им города,
     *                                                               принадлежащие городу улицы
     * Возращаемое значение имеет вид:
     * {ключСтраны : {
     *                 имяСтраны : {
     *                               ключГорода : {
     *                                              имяГорода : {
     *                                                            ключУлицы : имяУлицы
     *                                              }
     *                               }
     *                 }
     * }}
     */
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
                    }
                    if (cityIndex.get(rs.getInt(4)) == null) {
                        cityName = new HashMap<>();
                    } else {
                        cityName = cityIndex.get(rs.getInt(4));
                    }
                    if (cityName.get(rs.getString(5)) == null) {
                        city = new HashMap<>();
                    } else {
                        city = cityName.get(rs.getString(5));
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

    /**
     * Вспомогательный метод  проверки наличия в колекции пользователей конкретного пользователя
     * @param users - коллекция пользователей
     * @param userId - id пользователя, которого необходимо найти
     * @return true если такой пользователь есть
     */
    private boolean checkUsersMap(List<User> users, int userId) {
        boolean result = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == userId) {
                    result = true;
                    break;
                }
            }
        return result;
    }

    /**
     * Метод получения юзеров
     * @param type - типа запроса
     * @param address - коллекция адресов
     * @param role - искомое право доступа
     * @param types - коллекция типов музыки
     * @param stringForSearch - строка для поиска
     * @return коллекция юзеров, которые удовлетворяют запрашиваемым требованиям
     */
    public List<User> getUsers(String type, List<Integer> address, int role, List<Integer> types, String stringForSearch) {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement st = connection.prepareStatement("select * from users as u inner join address as a on u.u_address = a.id;");
            if (type.equals("address")) {
                st = connection.prepareStatement("select * from users as u inner join address as a on u.u_address = a.id where a.country_id = ? and a.city_id = ? and a.street_id = ?;");
                st.setInt(1, address.get(0));
                st.setInt(2, address.get(1));
                st.setInt(3, address.get(2));
            } else if (type.equals("role")) {
                st = connection.prepareStatement("select * from users as u inner join address as a on u.u_address = a.id where u_role = ?;");
                st.setInt(1, role);
            } else if (type.equals("types")) {
                String query = "select * from users as u inner join address as a on u.u_address = a.id inner join result_types as rt on u.id = rt.user_id where";
                for (int i = 0; i < types.size(); i++) {
                    if (i == 0) {
                        query += " rt.type_id = ?";
                    } else {
                        query += " or rt.type_id = ?";
                    }
                }
                query += ";";
                st = connection.prepareStatement(query);
                for (int i = 0; i < types.size(); i++)  {
                    st.setInt(i + 1, types.get(i));
                }
            } else if (type.equals("string")) {
                String query = "select * from users as u inner join address as a on u.u_address = a.id inner join result_types as rt on u.id = rt.user_id inner join music_type as mt on mt.id = rt.type_id inner join role as role on role.id = u.u_role where";
                query += " u.u_name like '%" + stringForSearch + "%'";
                query += " or u.u_login like '%" + stringForSearch + "%'";
                query += " or mt.name like '%" + stringForSearch + "%'";
                query += " or role.name like '%" + stringForSearch + "%';";
                st = connection.prepareStatement(query);
            }
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    if (!checkUsersMap(result, rs.getInt(1))) {
                        User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                        user.setId(rs.getInt(1));
                        user.setMusicTypes(this.getAllMusicTypesForUser(user.getId()));
                        List<Integer> adresses = new ArrayList<>();
                        adresses.add(rs.getInt(10));
                        adresses.add(rs.getInt(11));
                        adresses.add(rs.getInt(12));
                        user.setAddress(adresses);
                        result.add(user);
                    }
                }
            } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    /**
     * Метод получения пользователя по id
     * @param id - id пользователя для поиска
     * @return пользователь с таким id
     *          ИЛИ NULL если таког опользователя нет
     */
    public User getUserById(int id) {
        User user = null;
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement st = connection.prepareStatement("select * from users as u inner join address as a on u.u_address = a.id where u.id = ?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next())  {
                user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                user.setId(rs.getInt(1));
                user.setMusicTypes(this.getAllMusicTypesForUser(user.getId()));
                List<Integer> adresses = new ArrayList<>();
                adresses.add(rs.getInt(10));
                adresses.add(rs.getInt(11));
                adresses.add(rs.getInt(12));
                user.setAddress(adresses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Вспомогательный метод для наполнения таблицы в БД записями о типах музыки нового пользователя
     * @param user - обьект класса User, в котором хранится вся необходимая информация
     * @param connection - соединение с БД
     * @throws SQLException
     */
    private void fillMusicTypes(User user, Connection connection) throws SQLException {
        for (int i = 0; i < user.getMusicTypes().size(); i++) {
            PreparedStatement stMusicTypes = connection.prepareStatement("insert into result_types (user_id, type_id) values (?, ?);");
            stMusicTypes.setInt(1, user.getId());
            stMusicTypes.setInt(2, user.getMusicTypes().get(i));
            stMusicTypes.execute();
        }
    }

    /**
     * Метод обновления данных о пользователе в БД
     * @param newUser обьект класса User, который содержит в себе старый id и новые данные
     * @return true если обновление прошло успешно
     */
    public boolean updateUser(User newUser) {
        boolean result = true;
            try (Connection connection = SOURCE.getConnection()) {
                connection.setAutoCommit(false);
                try {
                    PreparedStatement address = connection.prepareStatement("update address set country_id = ?, city_id = ?, street_id = ? where user_id = ?;");
                    address.setInt(1, newUser.getAddress().get(0));
                    address.setInt(2, newUser.getAddress().get(1));
                    address.setInt(3, newUser.getAddress().get(2));
                    address.setInt(4, newUser.getId());
                    address.execute();
                    PreparedStatement deleteTypes = connection.prepareStatement("delete from result_types where user_id = ?;");
                    deleteTypes.setInt(1, newUser.getId());
                    deleteTypes.execute();
                    fillMusicTypes(newUser, connection);
                    PreparedStatement usr = connection.prepareStatement("update users set u_name = ?, u_login = ?, u_password = ?, u_role = ? where id = ?;");
                    usr.setString(1, newUser.getName());
                    usr.setString(2, newUser.getLogin());
                    usr.setString(3, newUser.getPassword());
                    usr.setInt(4, newUser.getRole());
                    usr.setInt(5, newUser.getId());
                    usr.execute();
                } catch (SQLException e) {
                    result = false;
                    connection.rollback();
                } finally {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                result = false;
                e.printStackTrace();
            }
        return result;
    }

    /**
     * Метод удаления пользователя
     * @param userId - id пользователя, которого необходимо удалить
     * @return true если удаление пользователя прошло успешно
     */
    public boolean deleteUser(int userId) {
        boolean result = true;
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement usr = connection.prepareStatement("delete from users where id = ?;");
                usr.setInt(1, userId);
                usr.execute();
                PreparedStatement address = connection.prepareStatement("delete from address where user_id = ?;");
                address.setInt(1, userId);
                address.execute();
                PreparedStatement deleteTypes = connection.prepareStatement("delete from result_types where user_id = ?;");
                deleteTypes.setInt(1, userId);
                deleteTypes.execute();
            } catch (SQLException e) {
                result = false;
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод проверки наличия пользователя по логину и паролю (необходимо для авторизации)
     * @param login - логин
     * @param password - пароль
     * @return обьект класса User, содержащий всю необходимую информацию
     *          ИЛИ NULL
     */
    public User checkUser(String login, String password) {
        User user = null;
        try (Connection connection = SOURCE.getConnection(); PreparedStatement st = connection.prepareStatement("select * from users where u_login = ? and u_password = ?;")) {
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("u_name"), rs.getString("u_login"), rs.getString("u_password"), rs.getInt("u_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
