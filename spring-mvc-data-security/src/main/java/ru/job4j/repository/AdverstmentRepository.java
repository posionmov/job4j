package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Adverstment;

public interface AdverstmentRepository extends CrudRepository<Adverstment, Integer> {
}
