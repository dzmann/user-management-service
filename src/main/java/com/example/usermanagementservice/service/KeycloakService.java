package com.example.usermanagementservice.service;

import com.example.usermanagementservice.dto.LoginDto;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakService {

    UserRepresentation createNewUser(UserRepresentation userRepresentation);

    UserRepresentation findById(String username);

    void deleteUser(String username);

    AccessTokenResponse getAccessToken(LoginDto loginDto);

}
