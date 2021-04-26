package ru.kirill.pimenov.pojo.bo;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoleBO implements GrantedAuthority {

    USER;

    @Override
    public String getAuthority() {
        return name();
    }

}
