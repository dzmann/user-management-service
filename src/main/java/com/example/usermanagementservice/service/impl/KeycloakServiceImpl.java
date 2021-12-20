package com.example.usermanagementservice.service.impl;

import com.example.usermanagementservice.dto.LoginDto;
import com.example.usermanagementservice.exception.ErrorResponse;
import com.example.usermanagementservice.exception.UserManagementException;
import com.example.usermanagementservice.helper.KeycloakClientHelper;
import com.example.usermanagementservice.service.KeycloakService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class KeycloakServiceImpl implements KeycloakService {

    @Autowired
    private KeycloakClientHelper keycloakClientHelper;

    public UserRepresentation findById(String username) {
        RealmResource realmResource = keycloakClientHelper.getRealmResource();
        List<UserRepresentation> found = realmResource.users().search(username);
        if(found.isEmpty()) {
            throw new UserManagementException("User not found", HttpStatus.NOT_FOUND);
        }
        return found.get(0);
    }

    public UserRepresentation createNewUser(UserRepresentation userRepresentation) {
        RealmResource realmResource = keycloakClientHelper.getRealmResource();
        UsersResource usersResource = realmResource.users();
        fillWithData(userRepresentation);
        Response response = usersResource.create(userRepresentation);

        if (response.getStatus() == HttpStatus.CREATED.value()) {
            String userId = CreatedResponseUtil.getCreatedId(response);
            finishUserCreation(usersResource, userId, userRepresentation);
            return realmResource.users().get(userId).toRepresentation();
        }

        ErrorResponse responseBody = response.readEntity(ErrorResponse.class);
        log.error(String.format("API response was: %s with status code %s", responseBody, response.getStatus()));
        throw new UserManagementException(responseBody, HttpStatus.valueOf(response.getStatus()));
    }

    public void deleteUser(String username) {
        UserRepresentation userRepresentation = findById(username);
        RealmResource realmResource = keycloakClientHelper.getRealmResource();
        realmResource.users().delete(userRepresentation.getId());
    }

    public AccessTokenResponse getAccessToken(LoginDto loginDto) {
        Keycloak keycloak = keycloakClientHelper.getClient(loginDto.getUserName(), loginDto.getPassword());
        return keycloak.tokenManager().getAccessToken();
    }

    private void finishUserCreation(UsersResource userResource, String userId, UserRepresentation userRepresentation) {
        UserResource savedUser = userResource.get(userId);
        savedUser.resetPassword(getCredentials(userRepresentation.getCredentials().get(0).getValue()));
        addRole(savedUser);
    }

    private CredentialRepresentation getCredentials(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        return credentialRepresentation;
    }

    private void addRole(UserResource userResource) {
        RoleRepresentation userRole = keycloakClientHelper.getRealmResource()
                .roles()
                .get("user")
                .toRepresentation();
        userResource
                .roles()
                .realmLevel()
                .add(Arrays.asList(userRole));
    }

    private void fillWithData(UserRepresentation userRepresentation) {
        userRepresentation.setEnabled(true);
    }

}
