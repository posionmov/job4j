package ru.job4j.models.cars;

/**
 * Класс-модель автомобиля
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class Car {

    /**
     * Поля класса:
     *      id автомобиля в бд
     *      название
     *      год выпуска
     *      пробег
     *      обьем двигателя
     *      мощность двигателя
     *      леворульный (да, нет)
     *      цена
     *      тип кузова
     *      марка автомобиля
     *      модель автомобиля
     *      коробка передач
     *      тип двигателя (бензин, дизель и тп)
     *      привод автомобиля (передний, задний, полный)
     *      цвет машины
     */
    private int id;                         // id автомобиля в бд
    private String descr;                   // название
    private int yearOfManufactured;         // год выпуска
    private int mileage;                    // пробег
    private float engineCapacity;           // обьем двигателя
    private float power;                    // мощность двигателя
    private boolean leftRudder;             // леворульный (да, нет)
    private float price;                    // цена
    private CarBodyType bodyType;           // тип кузова
    private CarMark mark;                   // марка автомобиля
    private CarTransmission transmission;   // коробка передач
    private CarEngine engine;               // тип двигателя (бензин, дизель и тп)
    private CarDrive drive;                 // привод автомобиля (передний, задний, полный)
    private CarColor carColor;              // цвет машины
    private CarModel carModel;              // модель автомобиля

    /**
     * Дефлоный конструктор класса
     */
    public Car() {

    }

    /**
     * Конструктор класса с полем id
     * @param id
     */
    public Car(int id) {
        this.id = id;
    }


    /**
     * Блок геттеров и сеттеров
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getYearOfManufactured() {
        return yearOfManufactured;
    }

    public void setYearOfManufactured(int yearOfManufactured) {
        this.yearOfManufactured = yearOfManufactured;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public float getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public boolean isLeftRudder() {
        return leftRudder;
    }

    public void setLeftRudder(boolean leftRudder) {
        this.leftRudder = leftRudder;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public CarBodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(CarBodyType bodyType) {
        this.bodyType = bodyType;
    }

    public CarMark getMark() {
        return mark;
    }

    public void setMark(CarMark mark) {
        this.mark = mark;
    }

    public CarTransmission getTransmission() {
        return transmission;
    }

    public void setTransmission(CarTransmission transmission) {
        this.transmission = transmission;
    }

    public CarEngine getEngine() {
        return engine;
    }

    public void setEngine(CarEngine engine) {
        this.engine = engine;
    }

    public CarDrive getDrive() {
        return drive;
    }

    public void setDrive(CarDrive drive) {
        this.drive = drive;
    }

    public CarColor getCarColor() {
        return carColor;
    }

    public void setCarColor(CarColor carColor) {
        this.carColor = carColor;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    /**
     * Переопределенный метод toString()
     * @return строка, описывающая всет екущие поля обьекта данного класса
     */
    @Override
    public String toString() {
        return "Car{" + "id=" + id
                + ", descr='" + descr + '\''
                + ", yearOfManufactured=" + yearOfManufactured
                + ", mileage=" + mileage
                + ", engineCapacity=" + engineCapacity
                + ", power=" + power
                + ", leftRudder=" + leftRudder
                + ", price=" + price
                + ", bodyType=" + bodyType
                + ", mark=" + mark
                + ", transmission=" + transmission
                + ", engine=" + engine
                + ", drive=" + drive
                + ", carColor=" + carColor
                + ", carModel=" + carModel + '}';
    }
}
