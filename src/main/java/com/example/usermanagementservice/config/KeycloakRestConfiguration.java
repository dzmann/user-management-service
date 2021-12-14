package com.example.usermanagementservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KeycloakRestConfiguration {

    @Value("${custom.keycloak.rest.client.server-url}")
    private String serviceUrl;

    /*@Value("${custom.keycloak.rest.client.clientSecret}")
    private String clientSecret;*/

}
