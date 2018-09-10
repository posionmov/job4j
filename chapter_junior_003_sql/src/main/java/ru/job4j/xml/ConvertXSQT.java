package ru.job4j.xml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Класс, производящий перевод XML файла в файл XSTL
 * @author Galanov Sergey
 * @since 10.09.2018
 * @version 1.0
 */
public class ConvertXSQT {

    /**
     * Приватное поле
     * Отражает количество строк, которыми необходимо наполнить файл
     */
    private int count;
    private Date date;
    private int timeLeft;

    /**
     * Конструктор класса
     * Создает обьект класса StoreXml с параметрами:
     *      1- файл для сохранения созданного им XML файла
     *      2- количество записей в БД
     * Псле этого конвертирует данные из XML файла в данные по маске scheme
     * @param source - относительный адрес файла, указывабщий на расположение простого xml файла
     * @param dest - относительный адрес файла, указывающий на расположение файла, в который необходимо сохранить
     *                                                                                          полученные данные
     * @param scheme - относительный адрес файла, указывающий на расположение файла с XSTL схемой
     * @param count - количество записей, которые необходимо создать в БД
     * @throws Exception
     */
    public ConvertXSQT(File source, File dest, File scheme, int count, Date date) throws Exception {
        this.date = date;
        this.count = count;
        StoreXml store = new StoreXml(source, this.count, this.date);
        this.timeLeft = store.getWorkTime();
        this.convert(source, dest, scheme);
    }

    /**
     * Метод конвертирования XML файла по маске
     * @param source - относительный адрес файла, указывабщий на расположение простого xml файла
     * @param dest - относительный адрес файла, указывающий на расположение файла, в который необходимо сохранить
     *                                                                                          полученные данные.
     *
     * @param scheme - относительный адрес файла, указывающий на расположение файла с XSTL схемой
     * @throws TransformerException
     * @throws UnsupportedEncodingException
     */
    public void convert(File source, File dest, File scheme) throws TransformerException, UnsupportedEncodingException {
        Date date = new Date();
        String before = this.convertFileToString(source);
        String rule = this.convertFileToString(scheme);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new ByteArrayInputStream(rule.getBytes())));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new StreamSource(new ByteArrayInputStream(before.getBytes("UTF-8"))),
                                                new StreamResult(new File(dest.getPath())));
        System.out.println(date.getTime() - this.date.getTime() + " - время выполнения операции конвертирования XML файла по маске");
        this.timeLeft -= date.getTime() - this.date.getTime();
    }

    /**
     * Метод, переводящий данные из файла в строку
     * @param file - путь к файлу, который необходимо перевести в строку
     * @return - строка, содержащая в себе все данные из файла
     */
    private String convertFileToString(File file) {
        String result = "";
        try {
            Date date = new Date();
            byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
            result = new String(encoded, Charset.defaultCharset());
            System.out.println(date.getTime() - this.date.getTime() + " - время выполнения операции перевода файла в строку");
            this.timeLeft -= date.getTime() - this.date.getTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getWorkTime() {
        System.out.println("Оставшееся время работы в ConvertXSQT - " + this.timeLeft);
        return this.timeLeft;
    }
}
