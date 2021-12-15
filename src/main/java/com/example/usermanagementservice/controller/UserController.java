package com.example.usermanagementservice.controller;

import com.example.usermanagementservice.dto.UserDto;
import org.dozer.DozerBeanMapper;
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

    @GetMapping
    public String testController() {
        return "accediste";
    }

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(userDto, UserRepresentation.class));
    }

}
