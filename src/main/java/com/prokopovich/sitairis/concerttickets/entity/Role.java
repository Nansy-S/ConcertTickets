package com.prokopovich.sitairis.concerttickets.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMINISTRATOR,
    MANAGER,
    USER;

    Role(){}

    @Override
    public String getAuthority() {
        return name();
    }
}
