package com.parking.parkingmanagement.constants;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public enum Role {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
