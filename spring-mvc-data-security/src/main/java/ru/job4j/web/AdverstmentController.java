package ru.job4j.web;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.service.AdverstmentService;

@Controller
@RequestMapping("/ads")
public class AdverstmentController {

    private final AdverstmentService service;

    @Autowired
    public AdverstmentController(AdverstmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok(new JSONObject().put("ads", this.service.getAll()).toString());
    }
}
