package ru.job4j.store;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import ru.job4j.models.Advertisement;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.models.cars.*;

/**
 * Класс интеграционных тестов для работы с БД
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class ValidateServiceTest {

    /**
     * Тест на добавление модели автомобиля в БД
     * Если модель добавлена, то можем ее получить
     */
    @Test
    public void whenAddModelThenCanFindHer() {
        CarModel model = new CarModel();
        model.setDescr("testModel");
        int result = ValidateService.INSTANCE.addModel(model);
        assertThat(result, is(1));
    }

    /**
     * Тест на добавление кузова автомобиля в БД
     * Если новый кузов добавлен, то его id должно быть 1
     */
    @Test
    public void whenAddBodyThenCanTakeHim() {
        CarBodyType body = new CarBodyType();
        body.setDescr("testBody");
        int result = ValidateService.INSTANCE.addBody(body);
        assertThat(result, is(1));
        assertThat(ValidateService.INSTANCE.getAllBodyTypes().get(0).getDescr(), is("testBody"));
    }

    /**
     * Тест на добавление коробки передач
     */
    @Test
    public void whenAddTransmissionThenCanFindHer() {
        CarTransmission transmission = new CarTransmission();
        transmission.setDescr("testTransmission");
        int result = ValidateService.INSTANCE.addTransmission(transmission);
        assertThat(result, is(1));
        assertThat(ValidateService.INSTANCE.getAllTransmissions().get(0).getDescr(), is("testTransmission"));
    }

    /**
     * Тест на добавление двигателя в БД
     */
    @Test
    public void whenAddEngineThenCatFindHim() {
        CarEngine engine = new CarEngine();
        engine.setDescr("testEngine");
        int result = ValidateService.INSTANCE.addEngine(engine);
        assertThat(result, is(1));
        assertThat(ValidateService.INSTANCE.getAllEngines().get(0).getDescr(), is("testEngine"));
    }

    /**
     * Тест добавления привода в БД
     */
    @Test
    public void whenAddDriveThenCanTakeHim() {
        CarDrive drive = new CarDrive();
        drive.setDescr("testDrive");
        int result = ValidateService.INSTANCE.addDrive(drive);
        assertThat(result, is(1));
        assertThat(ValidateService.INSTANCE.getAllDrives().get(0).getDescr(), is("testDrive"));
    }

    /**
     * Тест на добавление цвета автомобиля
     */
    @Test
    public void whenAddColorThenCamTakeHim() {
        CarColor color = new CarColor();
        color.setDescr("testColor");
        int result = ValidateService.INSTANCE.addCarColor(color);
        assertThat(result, is(1));
        assertThat(ValidateService.INSTANCE.getAllColors().get(0).getDescr(), is("testColor"));
    }

    /**
     * Тест на добавление марки автомобиля
     */
    @Test
    public void whenAddMarkThenCanTakeHer() {
        CarMark mark = new CarMark();
        mark.setDescr("testMark");
        int result = ValidateService.INSTANCE.addMark(mark);
        assertThat(result, is(1));
        assertThat(ValidateService.INSTANCE.getAllMarks().get(0).getDescr(), is("testMark"));
    }

    /**
     * Тест на добавление права доступа
     */
    @Test
    public void whenAddRoleThenCanTakeHim() {
        Role role = new Role();
        role.setName("testRole");
        int result = ValidateService.INSTANCE.addRole(role);
        assertThat(result, is(1));
        assertThat(ValidateService.INSTANCE.getAllRoles().get(0).getName(), is("testRole"));
    }

    /**
     * Тест на добавление автомобиля в БД
     */
    @Test
    public void whenAddCarThenCanFindHer() {
        Car car = new Car();
        car.setDescr("testCar");
        int result = ValidateService.INSTANCE.addCar(car).getId();
        assertThat(result, is(1));
        assertThat(ValidateService.INSTANCE.getAllCars().get(0).getDescr(), is("testCar"));
    }

    /**
     * Тест на добавление пользователя
     * Если мы добавляем пользователя, то можем проверить его существование в БД
     */
    @Test
    public void whenAddUserThenCanFindHim() {
        User user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        ValidateService.INSTANCE.addUser(user);
        assertNotNull(ValidateService.INSTANCE.checkUser(user));
    }

    /**
     * Тест на добавление обьявления
     * Если мы добавим обьявление, то можем его потом найти
     */
    @Test
    public void whenAddAdThenCanFindHim() {
        Advertisement ad = new Advertisement();
        ad.setDescription("testAd");
        ValidateService.INSTANCE.addAd(ad);
        assertThat(ValidateService.INSTANCE.getAllAd().size(), is(1));
        assertThat(ValidateService.INSTANCE.getAllAd().get(0).getDescription(), is("testAd"));
    }
}
