package ru.kirill.pimenov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kirill.pimenov.mapper.UserMapper;
import ru.kirill.pimenov.pojo.bo.UserBO;
import ru.kirill.pimenov.pojo.dto.UserDTO;
import ru.kirill.pimenov.pojo.entity.User;
import ru.kirill.pimenov.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserBO register (UserDTO userDTO) {
        User newUser = UserMapper.INSTANCE.fromUserDTO(userDTO);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return UserMapper.INSTANCE.toUserBO(userRepository.save(newUser));
    }
}
