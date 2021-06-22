package ru.kirill.pimenov.controller;

import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kirill.pimenov.pojo.bo.InviteBO;
import ru.kirill.pimenov.pojo.bo.TaskBO;
import ru.kirill.pimenov.pojo.bo.UserDetailsBO;
import ru.kirill.pimenov.pojo.dto.TaskDTO;
import ru.kirill.pimenov.service.TaskService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String get(Model model,
                      @AuthenticationPrincipal UserDetailsBO user) {
        List<TaskBO> tasks = taskService.getAll(user.getId());
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("{taskId}")
    public String get(Model model, @PathVariable UUID taskId) {
        TaskBO task = taskService.get(taskId);
        model.addAttribute("task", task);
        return "task";
    }

    @GetMapping("/invite")
    public String getByInvite(Model model,
                              @RequestParam UUID inviteId) {
        TaskBO task = taskService.getByInvite(inviteId);
        model.addAttribute("task", task);
        model.addAttribute("inviteId", inviteId);
        return "invite";
    }

    @GetMapping("/invites")
    public String getInvites(Model model,
                             @AuthenticationPrincipal UserDetailsBO user) {
        List<InviteBO> invites = taskService.getInvites(user.getUsername());
        model.addAttribute("invites", invites);
        return "invites";
    }

    @PostMapping
    public String add(Model model,
                      TaskDTO taskDTO,
                      @AuthenticationPrincipal UserDetailsBO user) {
        List<TaskBO> tasks = taskService.add(user, taskDTO);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/{taskId}")
    public String add(Model model,
                      TaskDTO taskDTO,
                      @PathVariable UUID taskId,
                      @AuthenticationPrincipal UserDetailsBO user) {
        taskService.addSubTask(taskId, user, taskDTO);
        TaskBO task = taskService.get(taskId);
        model.addAttribute("task", task);
        return "task";
    }

    @PostMapping("/invite")
    public String sendInvite(Model model,
                             @RequestParam UUID taskId,
                             @RequestParam String email,
                             @AuthenticationPrincipal UserDetailsBO user) {
        taskService.sendInvite(taskId, email, user);
        TaskBO task = taskService.get(taskId);
        model.addAttribute("task", task);
        return "task";
    }

    @PostMapping("/invite/react")
    public String reactInvite(Model model,
                             @RequestParam UUID inviteId,
                             @RequestParam boolean reaction,
                             @AuthenticationPrincipal UserDetailsBO user) {
        taskService.reactInvite(inviteId, reaction);
        List<TaskBO> tasks = taskService.getAll(user.getId());
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

}
