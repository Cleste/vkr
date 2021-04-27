package ru.kirill.pimenov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kirill.pimenov.pojo.bo.TaskBO;
import ru.kirill.pimenov.pojo.bo.UserDetailsBO;
import ru.kirill.pimenov.pojo.dto.TaskDTO;
import ru.kirill.pimenov.service.TaskService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public String get(Model model,
                      @AuthenticationPrincipal UserDetailsBO user) {
        List<TaskBO> tasks = taskService.getTasks(user);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/")
    public String add(Model model,
                      TaskDTO taskDTO,
                      @AuthenticationPrincipal UserDetailsBO user) {
        List<TaskBO> tasks = taskService.add(user, taskDTO);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

}
