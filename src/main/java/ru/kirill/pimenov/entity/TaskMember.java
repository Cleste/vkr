package ru.kirill.pimenov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task_member")
public class TaskMember extends IdentifiableEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private TaskRole taskRole;

    @ManyToOne
    @JoinColumn(name = "\"user\"")
    private User user;

    @ManyToOne
    @JoinColumn(name = "\"task\"")
    private Task task;

}
