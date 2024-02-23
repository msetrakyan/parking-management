package com.parking.parkingmanagement.constants;

import lombok.Getter;

@Getter
public enum Community {

    ZONE_A("ZONE_A"),
    ZONE_B("ZONE_B"),
    ZONE_C("ZONE_C"),
    ZONE_D("ZONE_D");

    private final String name;

    Community(String name) {
        this.name = name;
    }
}