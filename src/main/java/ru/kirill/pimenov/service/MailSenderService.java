package ru.kirill.pimenov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.kirill.pimenov.pojo.bo.UserDetailsBO;
import ru.kirill.pimenov.pojo.entity.User;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    private void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    public void sendActivationMail(User user) {
        String username = !StringUtils.isEmpty(user.getFirstName()) ? user.getEmail() : user.getFirstName();
        String message = String.format(
                "Уважаемый, %s! \n" +
                        "Добро пожаловать на Таскер. " +
                        "Перейдите по ссылке чтобы ативировать свой аккаунт - localhost:8080/activate/%s",
                username,
                user.getActivationCode()
        );
        send(user.getEmail(), "Активация аккаунта на tasker.ru", message);
    }

    public void sendInvite(UUID taskId, String email, User user) {
        String username = !StringUtils.isEmpty(user.getFirstName()) ? user.getEmail() : user.getFirstName();
        String message = String.format(
                "Пользователь %s пригласил вас на сайт tasker.ru. \n" +
                        "Чтобы увидеть задачу и принять приглашение перейдите по сслыке - localhost:8080/task/invite?inviteId=%s. ",
                username,
                taskId
        );
        send(email, "Пришлашение на веб-сайте tasker.ru", message);
    }
}
