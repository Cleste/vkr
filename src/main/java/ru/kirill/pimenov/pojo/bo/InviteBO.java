package ru.kirill.pimenov.pojo.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InviteBO {

    private UUID id;

    private TaskBO task;

}
