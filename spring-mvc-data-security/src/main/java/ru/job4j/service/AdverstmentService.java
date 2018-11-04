package ru.job4j.service;

import ru.job4j.domain.Adverstment;

import java.util.List;

public interface AdverstmentService {
    List<Adverstment> getAll();
    Adverstment add(Adverstment adverstment);
}
