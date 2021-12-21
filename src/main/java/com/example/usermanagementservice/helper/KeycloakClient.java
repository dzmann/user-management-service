package com.example.usermanagementservice.helper;

import com.example.usermanagementservice.config.KeycloakCustomProperties;
import com.example.usermanagementservice.exception.UserManagementException;
import lombok.AllArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotAuthorizedException;

@Component
@AllArgsConstructor
public class KeycloakClient {

    private final KeycloakCustomProperties properties;

    public RealmResource getRealmResource() {
        Keycloak keycloak = getClient(properties.getUserName(), properties.getPassword());
        return keycloak.realm(properties.getRealm());
    }

    public Keycloak getClient(String username, String password) {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(properties.getServerUrl())
                    .realm(properties.getRealm())
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(properties.getClientId())
                    .clientSecret(properties.getClientSecret())
                    .username(username)
                    .password(password)
                    .build();
            keycloak.tokenManager().getAccessToken();
            return keycloak;
        } catch (NotAuthorizedException notAuthorizedException) {
            throw new UserManagementException("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

}
