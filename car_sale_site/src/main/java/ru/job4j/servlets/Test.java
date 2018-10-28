package ru.job4j.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.json.JSONObject;
import ru.job4j.models.Advertisement;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.models.cars.*;
import ru.job4j.store.DbStore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws JsonProcessingException {
////        Добавление марки AUDI
//        CarMark audi = new CarMark();
//        audi.setDescr("audi");
//        List<CarModel> audiModels = new ArrayList<>();
//
//        CarModel audi1 = new CarModel();
//        audi1.setDescr("A 1");
//        audi1.setMark(audi);
//        CarModel audi2 = new CarModel();
//        audi2.setDescr("A 2");
//        audi2.setMark(audi);
//        CarModel audi3 = new CarModel();
//        audi3.setDescr("A 3");
//        audi3.setMark(audi);
//
//        audiModels.add(audi1);
//        audiModels.add(audi2);
//        audiModels.add(audi3);
//        audi.setModels(audiModels);
//        DbStore.INSTANCE.addMark(audi);
//
////        Добавление марки BMW
//        CarMark bmw = new CarMark();
//        bmw.setDescr("bmw");
//        List<CarModel> bmwModels = new ArrayList<>();
//
//        CarModel bmw1 = new CarModel();
//        bmw1.setDescr("X 1");
//        bmw1.setMark(bmw);
//        CarModel bmw2 = new CarModel();
//        bmw2.setDescr("X 2");
//        bmw2.setMark(bmw);
//        CarModel bmw3 = new CarModel();
//        bmw3.setDescr("X 3");
//        bmw3.setMark(bmw);
//
//        bmwModels.add(bmw1);
//        bmwModels.add(bmw2);
//        bmwModels.add(bmw3);
//        bmw.setModels(bmwModels);
//        DbStore.INSTANCE.addMark(bmw);
//
//        // Добавление типов кузовов
//        CarBodyType type1 = new CarBodyType();
//        type1.setDescr("Седан");
//        CarBodyType type2 = new CarBodyType();
//        type2.setDescr("Хэтчбэк");
//        DbStore.INSTANCE.addBody(type1);
//        DbStore.INSTANCE.addBody(type2);
//
//        // Добавление коробок передач
//        CarTransmission tr1 = new CarTransmission();
//        tr1.setDescr("Автомат");
//        CarTransmission tr2 = new CarTransmission();
//        tr2.setDescr("Механика");
//        DbStore.INSTANCE.addTransmission(tr1);
//        DbStore.INSTANCE.addTransmission(tr2);
//
//        // Добавление типа двигателя
//        CarEngine eng1 = new CarEngine();
//        eng1.setDescr("Бензин");
//        CarEngine eng2 = new CarEngine();
//        eng2.setDescr("Дизель");
//        DbStore.INSTANCE.addEngine(eng1);
//        DbStore.INSTANCE.addEngine(eng2);
//
//        //Добавление типов привода
//        CarDrive drive1 = new CarDrive();
//        drive1.setDescr("Полный");
//        CarDrive drive2 = new CarDrive();
//        drive2.setDescr("Задний");
//        DbStore.INSTANCE.addDrive(drive1);
//        DbStore.INSTANCE.addDrive(drive2);
//
//        // Добавление цетов
//        CarColor carColor1 = new CarColor();
//        CarColor carColor2 = new CarColor();
//        carColor1.setDescr("Черный");
//        carColor2.setDescr("Белый");
//        DbStore.INSTANCE.addCarColor(carColor1);
//        DbStore.INSTANCE.addCarColor(carColor2);
//
//        // Добавление ролей
//        Role role1 = new Role();
//        Role role2 = new Role();
//        role1.setName("Администратор");
//        role2.setName("Пользователь");
//        DbStore.INSTANCE.addRole(role1);
//        DbStore.INSTANCE.addRole(role2);
//
//        // Добавление пользователя
//        User user = new User();
//        user.setName("test user");
//        user.setLogin("login");
//        user.setPassword("password");
//        user.setEmail("example@ex.ru");
//        user.setRole(role1);
//        DbStore.INSTANCE.addUser(user);
//
//        // Добавление автомобиля
//        Car car = new Car();
//        car.setDescr("test car");
//        car.setYearOfManufactured(2018);
//        car.setMileage(0);
//        car.setEngineCapacity(2);
//        car.setPower(220);
//        car.setLeftRudder(true);
//        car.setPrice(100_000);
//        car.setBodyType(type1);
//        car.setMark(bmw);
//        car.setCarModel(bmw2);
//        car.setTransmission(tr1);
//        car.setEngine(eng1);
//        car.setDrive(drive1);
//        car.setCarColor(carColor1);
//        DbStore.INSTANCE.addCar(car);
//
//
        // Добавление обьявления
//        Advertisement ad = new Advertisement();
//        ad.setDescription("test ad");
//        ad.setCreateDate(new Timestamp(System.currentTimeMillis()));
//        ad.setCar(new Car(1));
//        ad.setUser(new User(1));
//        ad.setClose(false);
//        DbStore.INSTANCE.addAd(ad);

//        List<Advertisement> ads = DbStore.INSTANCE.getAllAd();
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> res = new LinkedHashMap<>();
//        res.put("res", ads);
//        System.out.println(mapper.writeValueAsString(res));

//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();
//
//        Query query = session.createQuery("from User where login = :login and password = :password");
//        query.setParameter("login", "login");
//        query.setParameter("password", "password1");
//        List list = query.list();
//        session.close();

//        System.out.println(list.size());

//        DbStore.INSTANCE.deleteAd(new Advertisement(21));

    }
}
