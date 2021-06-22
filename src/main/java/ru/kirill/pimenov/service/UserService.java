package ru.kirill.pimenov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kirill.pimenov.mapper.UserMapper;
import ru.kirill.pimenov.pojo.bo.UserBO;
import ru.kirill.pimenov.pojo.dto.UserDTO;
import ru.kirill.pimenov.pojo.entity.User;
import ru.kirill.pimenov.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailSenderService mailSenderService;

    public UserBO register(UserDTO userDTO) {
        User newUser = UserMapper.INSTANCE.fromUserDTO(userDTO);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setActive(false);
        newUser.setActivationCode(UUID.randomUUID());

        mailSenderService.sendActivationMail(newUser);

        return UserMapper.INSTANCE.toUserBO(userRepository.save(newUser));
    }

    public User getById(UUID id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public boolean activateUser(UUID code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActive(true);
        user.setActivationCode(null);
        return true;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
