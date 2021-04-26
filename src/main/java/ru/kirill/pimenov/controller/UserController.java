package ru.kirill.pimenov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kirill.pimenov.pojo.dto.UserDTO;
import ru.kirill.pimenov.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(UserDTO userDTO,
                               Model model) {
        userService.register(userDTO);

        model.addAttribute("messageType", "success");
        model.addAttribute("message", "На вашу почту отправлено письмо, перейдите по ссылке чтобы подтвердить свой аккаунт!");

        return "/login";
    }

}
