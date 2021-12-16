package com.example.usermanagementservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class UserManagementException extends RuntimeException {

    private int statusCode;

    public UserManagementException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
