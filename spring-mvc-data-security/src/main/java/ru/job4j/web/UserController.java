package ru.job4j.web;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.domain.Role;
import ru.job4j.domain.User;
import ru.job4j.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> getAllUsers() {
        System.out.println("get");
        List<User> users = this.service.getAll();
        return ResponseEntity.ok(new JSONObject().put("users", users).toString());
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        user.setRole(new Role(1));
        this.service.add(user);
        return ResponseEntity.ok(new JSONObject().put("add", "yes").toString());
    }
}
