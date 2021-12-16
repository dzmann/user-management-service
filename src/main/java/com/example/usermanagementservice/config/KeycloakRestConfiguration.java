package com.example.usermanagementservice.config;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "custom.keycloak.rest.client")
@Getter
@Setter
public class KeycloakRestConfiguration {

    private String serverUrl;
    private String realm;
    private String clientId;
    private String clientSecret;
    private String userName;
    private String password;

    @Bean("agenciaRealmResource")
    public RealmResource realmResource() {
        Keycloak keycloak = KeycloakBuilder.builder() //
                .serverUrl(serverUrl) //
                .realm(realm) //
                .grantType(OAuth2Constants.PASSWORD) //
                .clientId(clientId) //
                .clientSecret(clientSecret)
                .username(userName)
                .password(password)
                .build();
        keycloak.tokenManager().getAccessToken();

        return keycloak.realm(realm);
    }

}
