package ru.kirill.pimenov.service;

import lombok.RequiredArgsConstructor;
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

    public UserBO register (UserDTO userDTO) {
        User newUser = UserMapper.INSTANCE.fromUserDTO(userDTO);

        return UserMapper.INSTANCE.toUserBO(userRepository.save(newUser));
    }
}
