package ru.job4j.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс для сохранения данных из БД в xml файл
 * @author Galanov Sergey
 * @since 19.09.2018
 * @version 1.1
 */
public class StoreXml {

    /**
     * Приватные поля класса:
     * Обьект класса File
     * Количество создаваемых записей в БД
     */
//    private File file;
//    private int count;
    private int timeLeft;
    private Date date;

    /**
     * Класс, описывающий коллекцию List, содержащую обьекты класса Entry
     */
    @XmlRootElement
    public static class Entries {

        /**
         * Приватное поле класса
         * Содержит коллекцию List, содержащую в себе обьекты класса Entry
         */
        private List<Entry> values;

        /**
         * Конструктор класса
         */
        public Entries() {
        }

        /**
         * Не дефолтный конструктор класса
         * Задает дефолтные значения приватной коллекции
         * @param values
         */
        public Entries(List<Entry> values) {
            this.values = values;
        }

        /**
         * Метод получения приватной коллекции
         * @return коллекцию обьектов Entry
         */
        public List<Entry> getValues() {
            return values;
        }

        /**
         * Метод, задающий всю коллекцию
         * @param values - коллекция List с обьектами класса Entry
         */
        public void setValues(List<Entry> values) {
            this.values = values;
        }
    }

    /**
     * Класс, описывающий обьекты, которые будт раниться в коллекции
     */
    @XmlRootElement
    public static class Entry {

        /**
         * Приватное поле
         * Содержит число
         */
        private int value;

        /**
         * Конструктор класса
         */
        public Entry() {
        }

        /**
         * Колнструктор класса, задающий дефолтное значение числу данного обьекта
         * @param value - значение данного обьекта
         */
        public Entry(int value) {
            this.value = value;
        }

        /**
         * Геттер значения данного обьекта
         * @return значение данного обьекта
         */
        public int getValue() {
            return value;
        }

        /**
         * Сеттер значения данного обьекта
         * @param value значение для данного обьекта
         */
        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * Конструктор данного класса
     * Принимает в качестве аргументов следующие значения:
     * @param file - файл, в который необходимо сохранить созданную XML схему
     * @param count - количество значений, которые будут сохранены в БД и в последующем сохранены в XML
     * После этого Вызывает методы наполнения БД знаениями и сохранение данных значений в обьект класса Entries
     *              После этого данный обьект парсится и сохраняется в файл
     * @throws Exception
     */
    public StoreXml(File file, int count,int timeLeft) throws Exception {
        //this.timeLeft = timeLeft;
        this.start(file, count, timeLeft);
    }

    private void start(File file, int count, int timeLeft) throws Exception {
        this.save(this.getEntries(count, timeLeft), file);
    }

    /**
     * Метод сохранения в файл данных из коллекции (обьекта класса Entries)
     * @param list обьект класса Entries
     * @param file относительный путь файла
     * @throws JAXBException
     */
    private void save(Entries list, File file) throws JAXBException {
        Date date = new Date();
        System.out.println("SAVE ---- " + file.getPath());
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(list, file);
        System.out.println((new Date().getTime() - date.getTime()) + " - время выполнения операции сохранения нового xml");
    }

    /**
     * Метод, производящий наполнение БД (посредством создания обьекта класса StoreSQL
     *      с параметрами количества элементов), а так же сохранения данных из БД в коллекцию.
     * @return объект класса Entries, который уже содержит все данные из БД
     */
    private Entries getEntries(int count, int timeLeft) throws ClassNotFoundException {
        StoreSQL store = new StoreSQL("sqllite.properties", timeLeft);
        store.generate(count);
        this.timeLeft = store.getWorkTime();
        List<Integer> res = store.getValuesFromBD();
        List<Entry> list = new ArrayList<>();
        for (Integer integer : res) {
            list.add(new Entry(integer));
        }
        Entries result = new Entries(list);
        this.timeLeft = store.getWorkTime();
        return result;
    }

    public int getWorkTime() {
        System.out.println("Оставшееся время работы в StoreXML - " + this.timeLeft);
        return this.timeLeft;
    }
}
