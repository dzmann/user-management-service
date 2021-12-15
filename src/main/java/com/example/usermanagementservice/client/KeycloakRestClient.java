package com.example.usermanagementservice.client;

import com.example.usermanagementservice.config.KeycloakRestConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KeycloakRestClient {

    private final KeycloakRestConfiguration keycloakRestConfiguration;


}
