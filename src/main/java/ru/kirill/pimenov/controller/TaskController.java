package ru.kirill.pimenov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TaskController {

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "tasks";
    }

}
