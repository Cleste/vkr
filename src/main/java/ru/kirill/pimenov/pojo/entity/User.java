package ru.kirill.pimenov.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Пользователь
 */
@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private List<TaskMember> tasksMembership = new ArrayList<>();

}
