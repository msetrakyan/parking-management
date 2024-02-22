package com.parking.parkingmanagement.util;

import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.user.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {

    public static Integer getId() {
        UserEntity principal = (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getId();
    }

    public static String getUsername() {
        UserEntity principal = (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getUsername();
    }

    public static Community getCommunity() {
        UserEntity principal = (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getCommunity();
    }

}