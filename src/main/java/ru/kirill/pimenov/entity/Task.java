package ru.kirill.pimenov.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "task")
public class Task extends IdentifiableEntity {

    @OneToMany(mappedBy = "task", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private List<TaskMember> taskMembers = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "master")
    private Task master;

    @Column(name = "description")
    private String text;

    @Column(name = "theme")
    private String theme;
}
