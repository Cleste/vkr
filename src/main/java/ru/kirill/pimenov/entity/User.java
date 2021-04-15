package ru.kirill.pimenov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Пользователь
 */
@Entity
@Table(name = "user")
public class User extends IdentifiableEntity {

    /**
     * Электронный адрес
     */
    @Column(name = "email")
    private String email;

    /**
     * Имя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name")
    private String middleName;

}
