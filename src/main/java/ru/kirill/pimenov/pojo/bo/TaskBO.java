package ru.kirill.pimenov.pojo.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TaskBO {

    private UUID id;

    private String text;

    private String theme;

}
