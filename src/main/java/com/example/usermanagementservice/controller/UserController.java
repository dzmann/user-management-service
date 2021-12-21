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
import org.springframework.web.bind.annotation.*;

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
    public AccessTokenResponse login(@Valid @RequestBody LoginDto loginDto) {
        return keycloakService.getAccessToken(loginDto);
    }

    @GetMapping(value = "/username/{username}")
    @RolesAllowed({"admin", "user"})
    public UserDto getByUsername(@PathVariable(value = "username") String username) {
        return mapper.map(keycloakService.findByUsername(username), UserDto.class);
    }

    @GetMapping(value = "{id}")
    @RolesAllowed({"admin", "user"})
    public UserDto getById(@PathVariable(value = "id") String id) {
        return mapper.map(keycloakService.findById(id), UserDto.class);
    }

    @PostMapping(value = "/create")
    @RolesAllowed({"admin"})
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto) {
        UserRepresentation createdUser = keycloakService.createNewUser(mapper.map(userDto, UserRepresentation.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(createdUser, UserDto.class));
    }

    @PutMapping(value = "{id}")
    @RolesAllowed({"admin", "user"})
    public ResponseEntity updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(value = "id") String id) {
        UserRepresentation updatedUser = keycloakService.updateUser(mapper.map(userDto, UserRepresentation.class), id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(updatedUser, UserDto.class));
    }

    @DeleteMapping(value = "{id}")
    @RolesAllowed({"admin"})
    public void delete(@PathVariable(value = "id") String id) {
        keycloakService.deleteUser(id);
    }

}
