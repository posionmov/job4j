package ru.job4j.threads;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Класс для запуска отображения и передвижения квадрата по заданному полю
 * @author Петр Арсентьев
 */
public class PingPong extends Application {

    /**
     * Содержит приватные поля класса
     * JOB4J - константа, которая будет отображаться в заголовке
     */
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    /**
     * Переопределенный метод класса Applications
     * Запускает приложение и закрывает его при необходимости
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Thread thread =  new Thread(new RectangleMove(rect, limitX, limitY));
        thread.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(
                event -> thread.interrupt()
        );
    }
}
