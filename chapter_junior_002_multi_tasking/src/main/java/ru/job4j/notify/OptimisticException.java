package ru.job4j.notify;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String msg) {
        super(msg);
    }
}
