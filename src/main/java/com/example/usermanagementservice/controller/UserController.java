package com.example.usermanagementservice.controller;

import com.example.usermanagementservice.dto.UserDto;
import com.example.usermanagementservice.service.KeycloakService;
import org.dozer.Mapper;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private KeycloakService keycloakService;

    @GetMapping
    public String testController() {
        return "accediste";
    }

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto) {
        UserRepresentation createdUser = keycloakService.createNewUser(mapper.map(userDto, UserRepresentation.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(createdUser, UserDto.class));
    }

}
