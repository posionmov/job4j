package ru.job4j.xml_example;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Date;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

/**
 * Класс для парсинга данных из XSTL файла
 * @author Galanov Sergey
 * @since 10.09.2018
 * @version 1.0
 */
public class ResultSumm extends DefaultHandler {

    /**
     * Приватные поля класса
     * Содержит:
     *  - Итоговую сумму всех чисел
     *  - Ссылки на файлы:
     *      а) Файла XML
     *      б) файла XSTL
     *      в) файла, содержащего маску перевода XML в XSTL
     */
    private double result; // Итоговая сумма
    private int timeLeft;
    private final static File source = new File("./src/main/java/ru/job4j/xml_example/XML.xml");
    private final static File dest = new File("./src/main/java/ru/job4j/xml_example/XLS_result.xml");
    private final static File scheme = new File("./src/main/java/ru/job4j/xml_example/XLS_scheme.xml");

    /**
     * Конструктор класса
     * Создает обьект класса Date, ConvertXSQT, Helper
     * Затем вызывает приватный метод doParse
     * После этого получает из обьекта класса Helper итоговое значение и задает его у себя
     * @param n - количество обьектов, создаваемых в БД
     * @throws Exception
     */
    public ResultSumm(int n) throws Exception {
        Date date = new Date();
        ConvertXSQT convertXSQT = new ConvertXSQT(source, dest, scheme, n, date);
        this.timeLeft = convertXSQT.getWorkTime();
        System.out.println(this.timeLeft + " - оставшееся время после всех пераций перед парсингом");
        Helper helper = new Helper();
        helper.timeLeft = this.timeLeft;
        this.doParse(helper);
        this.result = helper.getResult();
    }

    /**
     * Приватный метод, произвдящий парсинг файла
     * @param helper
     * @throws Exception
     */
    private void doParse(Helper helper) throws Exception{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(dest, helper); // данный метод начинает парсить файл при помощи класса, наследующего класс DefaultHandler
    }

    /**
     * Геттер единственного приватного поля
     * @return - итоговое значение
     */
    public double getResult() {
        return this.result;
    }

    /**
     * Приватный класс, производящий парсинг сайта
     */
    private class Helper extends DefaultHandler {

        /**
         * Приватные поля класса:
         *  - переменная, хранящая результат
         *  - обьект класса Date, который создается при создании обьекта данного класса
         */
        private double result = 0;
        private Date date = new Date();
        public int timeLeft;

        /**
         * Метод, вызывающийся при нахождении в конце документа
         * Вызывает метод класса DefaultHandler, который посылает в SAXParserFactory уведомление, что файл прочитан
         * @throws SAXException
         */
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            System.out.println(this.result);
        }

        /**
         * Метод, срабатывающий при каждом нахождении тэга элемента в файла
         * В данном примере если значение тэга = "entry" и таймер менее 5 минут, то в приватное поле result прибавлется
         *                                новое значение из атрибута "href"
         * @param uri
         * @param localName
         * @param qName
         * @param attributes
         * @throws SAXException
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            Date date = new Date();
            if (qName.equalsIgnoreCase("entry") && this.checkTime(date)) {
                this.result += Integer.valueOf(attributes.getValue("href"));
            } else if (!this.checkTime(date)) {
                System.out.println("Больше 5 минут");
                super.endDocument();
            }
        }

        /**
         * Прстой геттер приватного поля, описывающего итоговое значение
         * @return итоговое значение приватного поля
         */
        public double getResult() {
            return this.result;
        }

        /**
         * Метод проверки веремени
         * @param newTime - обьект класса Data
         * @return true если прошедшее время с начала работы менее 5 минут
         */
        private boolean checkTime(Date newTime) {
            this.timeLeft -= date.getTime() - this.date.getTime();
            return this.timeLeft > 0;
        }
    }
}
