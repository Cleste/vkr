package ru.kirill.pimenov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment extends IdentifiableEntity {

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "\"user\"")
    private User user;

    @ManyToOne
    @JoinColumn(name = "\"task\"")
    private Task task;

}
