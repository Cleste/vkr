package ru.kirill.pimenov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.kirill.pimenov.pojo.bo.UserBO;
import ru.kirill.pimenov.pojo.bo.UserDetailsBO;
import ru.kirill.pimenov.pojo.dto.UserDTO;
import ru.kirill.pimenov.pojo.entity.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserBO toUserBO(User user);

    User fromUserDTO(UserDTO userDTO);

    @Mapping(target = "username", source = "email")
    UserDetailsBO toUserDetailsBO(User user);

}
