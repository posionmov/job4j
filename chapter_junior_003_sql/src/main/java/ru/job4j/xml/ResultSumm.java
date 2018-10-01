package ru.job4j.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Objects;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

/**
 * Класс для парсинга данных из XSTL файла
 * @author Galanov Sergey
 * @since 19.09.2018
 * @version 1.1
 */
public class ResultSumm extends DefaultHandler {

    /**
     * Приватные поля класса
     * Содержит:
     *  - обьекты класса File, читаемые через classloader
     *  - итоговая сумма
     *  оставшееся время
     *  - текущую дату
     */
    private File source, dest, scheme;
    private double result; // Итоговая сумма
    private int timeLeft;
    private Date date;


    /**
     * Конструктор класса
     * Задает значения ссылок на файлы (которые должны лежить в той же папке), а также
     */
    public ResultSumm() {
        this.date = new Date();
        this.timeLeft = 300_000; // эквивалентно 5 минутам
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
//            this.source = new File(Objects.requireNonNull(classLoader.getResource("XML.xml")).getFile());
//            this.dest = new File(classLoader.getResource("XLS_result.xml").getFile());
//            this.scheme = new File(classLoader.getResource("XLS_scheme.xml").getFile());

            URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
            System.out.println("path  ------   " + location.getPath());
            this.source = new File(location.getPath() + "/XML.xml");
            this.dest = new File(location.getPath() + "/XLS_result.xml");
            this.scheme = new File(location.getPath() + "/XLS_scheme.xml");

            System.out.println("source " + this.source.isFile());
            System.out.println("dest " + this.dest.isFile());
            System.out.println("scheme " + this.scheme.isFile());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, запускающий работу всей программы
     * Создает обьект ConvertXSQT, Helper
     * Затем вызывает приватный метод doParse
     * После этого получает из обьекта класса Helper итоговое значение и задает его в приватное поле
     * @param n - количество обьектов, создаваемых в БД
     * @throws Exception
     */
    public void start(int n) throws Exception {
        ConvertXSQT convertXSQT = new ConvertXSQT(source, dest, scheme, n, this.timeLeft);
        Helper helper = new Helper();
        helper.timeLeft = convertXSQT.getWorkTime();
        this.doParse(helper);
        this.result = helper.getResult();
    }

    /**
     * Приватный метод, произвдящий парсинг файла
     * @param helper
     * @throws Exception
     */
    private void doParse(Helper helper) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(this.dest, helper); // данный метод начинает парсить файл при помощи класса, наследующего класс DefaultHandler
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
