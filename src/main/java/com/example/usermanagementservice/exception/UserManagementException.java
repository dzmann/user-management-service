package com.example.usermanagementservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class UserManagementException extends RuntimeException {

    private HttpStatus statusCode;
    private ErrorResponse errorResponse;

    public UserManagementException(ErrorResponse message, HttpStatus statusCode) {
        this.errorResponse = message;
        this.statusCode = statusCode;
    }

    public UserManagementException(String message, HttpStatus httpStatus) {
        this.errorResponse = new ErrorResponse(message);
        this.statusCode = httpStatus;
    }

}
