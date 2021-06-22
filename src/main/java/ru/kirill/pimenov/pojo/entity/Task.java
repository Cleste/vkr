package ru.kirill.pimenov.pojo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Задача
 */
@Entity
@Getter
@Setter
@Table(name = "task")
public class Task extends IdentifiableEntity {

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "task", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TaskMember> taskMembers = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "task", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "master")
    private Task master;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "master", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Task> subTasks = new ArrayList<>();

    @Column(name = "description")
    private String text;

    @Column(name = "theme")
    private String theme;

}
