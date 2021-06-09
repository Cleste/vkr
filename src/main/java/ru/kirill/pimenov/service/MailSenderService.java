package ru.kirill.pimenov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.kirill.pimenov.pojo.entity.User;

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
                        "Добро поаловать на Таскер. " +
                        "Перейдите по ссылке чтобы ативировать свой аккаунт - localhost:8080/activate/%s",
                username,
                user.getActivationCode()
        );
        send(user.getEmail(), "Активация аккаунта на tasker.ru", message);
    }
}
