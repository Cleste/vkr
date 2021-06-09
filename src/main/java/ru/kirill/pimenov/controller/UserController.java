package ru.kirill.pimenov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kirill.pimenov.pojo.dto.UserDTO;
import ru.kirill.pimenov.service.UserService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(UserDTO userDTO, Model model) {

        userService.register(userDTO);

        model.addAttribute("messageType", "success");
        model.addAttribute("message", "На вашу почту отправлено письмо, перейдите по ссылке чтобы подтвердить свой аккаунт!");

        return "/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable UUID code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Пользователь успешно активирован");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Код или пользователь не найден");
        }
        return "login";
    }

}
