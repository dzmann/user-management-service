package com.example.usermanagementservice.exception;

import lombok.Getter;
import org.keycloak.authorization.client.util.Http;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class UserManagementException extends RuntimeException {

    private HttpStatus statusCode;
    private ErrorResponse errorResponse;

    public UserManagementException(String message, HttpStatus statusCode) {
        this.errorResponse = new ErrorResponse(message);
        this.statusCode = statusCode;
    }
}
