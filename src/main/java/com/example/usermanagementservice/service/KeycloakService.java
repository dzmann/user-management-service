package com.example.usermanagementservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Arrays;

@Component
@AllArgsConstructor
@Slf4j
public class KeycloakService {

    @Qualifier("fooFormatter")
    private final RealmResource agenciaRealmResource;

    public UserRepresentation createNewUser(UserRepresentation userRepresentation) {
        UsersResource usersResource = agenciaRealmResource.users();
        fillWithData(userRepresentation);
        Response response = usersResource.create(userRepresentation);

        if(response.getStatus() == HttpStatus.CREATED.value()) {
            String userId = CreatedResponseUtil.getCreatedId(response);
            finishUserCreation(usersResource, userId, userRepresentation);
            return agenciaRealmResource.users().get(userId).toRepresentation();
        }
        log.error(String.format("API response HttpStatus %s", response.getStatus()));
        throw new RuntimeException("An error ocurred while creating user");
    }

    private void finishUserCreation(UsersResource userResource, String userId, UserRepresentation userRepresentation) {
        UserResource savedUser = userResource.get(userId);
        savedUser.resetPassword(getCredentials(userRepresentation.getCredentials().get(0).getValue()));
    }

    private CredentialRepresentation getCredentials(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        return credentialRepresentation;
    }

    private void addRole(UserResource userResource) {
        RoleRepresentation userRole = agenciaRealmResource.roles()
                .get("user").toRepresentation();
        userResource.roles().realmLevel() //
                .add(Arrays.asList(userRole));
    }

    private void fillWithData(UserRepresentation userRepresentation) {
        userRepresentation.setEnabled(true);
    }

}
