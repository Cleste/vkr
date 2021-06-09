package ru.kirill.pimenov.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Пользователь
 */
@Entity
@Getter
@Setter
@Table(name = "\"user\"")
public class User extends IdentifiableEntity {

    /**
     * Электронный адрес
     */
    @Column(name = "email")
    private String email;

    /**
     * Пароль
     */
    @Column(name = "password")
    private String password;

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

    /**
     * Признак активности пользователя
     */
    @Column(name = "active")
    private boolean active;

    /**
     * Код активации пользователя
     */
    @Column(name = "activation_code")
    private UUID activationCode;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TaskMember> tasksMembership = new ArrayList<>();

}
