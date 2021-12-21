package com.example.usermanagementservice.service;

import com.example.usermanagementservice.dto.LoginDto;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakService {

    UserRepresentation createNewUser(UserRepresentation userRepresentation);

    UserRepresentation updateUser(UserRepresentation userRepresentation, String id);

    UserRepresentation findByUsername(String username);

    UserRepresentation findById(String id);

    void deleteUser(String id);

    AccessTokenResponse getAccessToken(LoginDto loginDto);

}
