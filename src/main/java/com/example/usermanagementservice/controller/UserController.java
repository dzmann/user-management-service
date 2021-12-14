package com.example.usermanagementservice.controller;

import com.example.usermanagementservice.config.KeycloakRestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private KeycloakRestConfiguration restConfiguration;

    @GetMapping
    public String getData() {
        return restConfiguration.getServiceUrl();
    }
}
