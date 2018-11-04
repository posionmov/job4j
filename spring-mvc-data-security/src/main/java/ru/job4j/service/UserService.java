package ru.job4j.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.job4j.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User add(User user);
    UserDetails loadUserByUsername(String username);
}
