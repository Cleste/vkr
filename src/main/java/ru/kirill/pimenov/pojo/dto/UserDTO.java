package ru.kirill.pimenov.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    /**
     * Идентификатор пользователя
     */
    private UUID id;

    /**
     * Электронный адрес
     */
    private String email;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Отчество
     */
    private String middleName;

}
