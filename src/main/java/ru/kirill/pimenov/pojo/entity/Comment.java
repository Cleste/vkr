package ru.kirill.pimenov.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment extends IdentifiableEntity {

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "\"user\"")
    private User user;

    @ManyToOne
    @JoinColumn(name = "\"task\"")
    private Task task;

}