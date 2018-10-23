package ru.job4j.models;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ru.job4j.storage.ValidateService;

import javax.persistence.OptimisticLockException;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.*;

/**
 * Класс для тестирования CRUD операций с автомобилями в БД
 * @author Galanov Sergey
 * @since 23.10.2018
 * @version 1.0
 */
public class CarTest {

    /**
     * Приватные поля класса
     * Содержат id создаваемых обьектов
     */
    private static int carId, bodyId, engineId, transmissionId, newBodyId;

    /**
     * Метод, вызывающийся ДО ЗПУСКА ДАНОГО КЛАССА
     * сохраняет в БД 1 кузов, 1 двигатель, 1 трансмиссию
     */
    @BeforeClass
    public static void beforeTests() {
        Body body = new Body();
        Engine engine = new Engine();
        Transmission transmission = new Transmission();
        body.setDescription("body test");
        engine.setDescription("engine test");
        transmission.setDescription("transmission test");
        bodyId = ValidateService.INSTANCE.addBody(body);
        engineId = ValidateService.INSTANCE.addEngine(engine);
        transmissionId = ValidateService.INSTANCE.addTransmission(transmission);
    }

    /**
     * Метод, отрабатывающий ПОСЛЕ ВЫПОЛНЕНИЯ ТЕСТОВ ДАННОГО КЛАССА
     * Удаляет из бд записи об 1 кузове, 1 двигателе, 1 трансмиссии
     */
    @AfterClass
    public static void afterTest() {
        ValidateService.INSTANCE.deleteBody(new Body(bodyId));
        ValidateService.INSTANCE.deleteEngine(new Engine(engineId));
        ValidateService.INSTANCE.deleteTransmission(new Transmission(transmissionId));
    }

    /**
     * Тест на добавление и получение данных из бд
     */
    @Test
    public void whenCreateCarThenCanTakeAllFields() {
        Car car = new Car();
        car.setDescription("test car");
        car.setBody(new Body(bodyId));
        car.setEngine(new Engine(engineId));
        car.setTransmission(new Transmission(transmissionId));
        carId = ValidateService.INSTANCE.addCar(car);
        assertThat(ValidateService.INSTANCE.getCarFromId(carId).getBody().getDescription(), is("body test"));
        assertThat(ValidateService.INSTANCE.getCarFromId(carId).getEngine().getDescription(), is("engine test"));
        assertThat(ValidateService.INSTANCE.getCarFromId(carId).getTransmission().getDescription(), is("transmission test"));
        ValidateService.INSTANCE.deleteCar(car);
    }

    /**
     * Тест на обновление данных в БД
     */
    @Test
    public void whenUpdateCarThenCanGetChangedFields() {
        Car car = new Car();
        car.setDescription("test car");
        car.setBody(new Body(bodyId));
        car.setEngine(new Engine(engineId));
        car.setTransmission(new Transmission(transmissionId));
        carId = ValidateService.INSTANCE.addCar(car);
        Body body = new Body();
        body.setDescription("body_test_2");
        newBodyId = ValidateService.INSTANCE.addBody(body);
        car.setBody(new Body(newBodyId));
        ValidateService.INSTANCE.updateCar(car);
        assertThat(ValidateService.INSTANCE.getCarFromId(carId).getBody().getDescription(), is("body_test_2"));
        ValidateService.INSTANCE.deleteCar(car);
        ValidateService.INSTANCE.deleteBody(new Body(newBodyId));
    }

    /**
     * Тест на удаление данных из бд
     * Должен выкинуть исключение так как обьект класса Car уже удален из БД
     */
    @Test
    public void whenDeleteCarThenCantGetThey() {
        Car car = new Car();
        car.setDescription("test car");
        car.setBody(new Body(bodyId));
        car.setEngine(new Engine(engineId));
        car.setTransmission(new Transmission(transmissionId));
        carId = ValidateService.INSTANCE.addCar(car);
        ValidateService.INSTANCE.deleteCar(car);
        assertThrows(OptimisticLockException.class, () -> {
            ValidateService.INSTANCE.deleteCar(new Car(carId));
        });
    }
}