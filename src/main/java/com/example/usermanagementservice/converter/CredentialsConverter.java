package com.example.usermanagementservice.converter;

import org.dozer.CustomConverter;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.Arrays;
import java.util.List;

public class CredentialsConverter implements CustomConverter {

    @Override
    public Object convert(Object o, Object o1, Class<?> aClass, Class<?> aClass1) {
        if(o == null) {
            String password = (String) o1;
            return getCredentialRepresentationList(password);
        }
        return null;
    }


    private List<CredentialRepresentation> getCredentialRepresentationList(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(password);
        return Arrays.asList(credentialRepresentation);
    }



}
