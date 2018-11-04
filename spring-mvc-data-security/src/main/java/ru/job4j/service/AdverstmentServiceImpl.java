package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Adverstment;
import ru.job4j.repository.AdverstmentRepository;

import java.util.List;

@Service
public class AdverstmentServiceImpl implements AdverstmentService {

    private final AdverstmentRepository repository;

    @Autowired
    public AdverstmentServiceImpl(AdverstmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Adverstment> getAll() {
        return (List<Adverstment>) this.repository.findAll();
    }

    @Override
    public Adverstment add(Adverstment adverstment) {
        return this.repository.save(adverstment);
    }
}
