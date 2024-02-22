package com.parking.parkingmanagement.controller;


import com.parking.parkingmanagement.model.user.UserCreateRequest;
import com.parking.parkingmanagement.model.user.UserDto;
import com.parking.parkingmanagement.model.user.UserEntity;
import com.parking.parkingmanagement.service.user.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    @Secured("ADMIN")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.ok(userService.create(userCreateRequest));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok(userService.update(userEntity));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }





}
