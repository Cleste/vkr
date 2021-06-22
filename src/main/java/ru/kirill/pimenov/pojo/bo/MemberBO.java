package ru.kirill.pimenov.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MemberBO {

    private String email;

    private UUID id;

}
