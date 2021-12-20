package com.example.usermanagementservice.controller;

import com.example.usermanagementservice.dto.LoginDto;
import com.example.usermanagementservice.dto.UserDto;
import com.example.usermanagementservice.service.KeycloakService;
import org.dozer.Mapper;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private KeycloakService keycloakService;

    @PostMapping(value = "/login")
    @RolesAllowed({"admin", "user"})
    public AccessTokenResponse login(@Valid @RequestBody LoginDto loginDto) {
        return keycloakService.login(loginDto);
    }

    @PostMapping
    @RolesAllowed({"admin"})
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto) {
        SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        UserRepresentation createdUser = keycloakService.createNewUser(mapper.map(userDto, UserRepresentation.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(createdUser, UserDto.class));
    }

}
