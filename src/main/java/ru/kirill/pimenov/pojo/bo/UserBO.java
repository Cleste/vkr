package ru.kirill.pimenov.pojo.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserBO {

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
