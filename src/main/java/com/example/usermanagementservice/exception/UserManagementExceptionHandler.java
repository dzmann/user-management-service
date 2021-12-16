package com.example.usermanagementservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UserManagementExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserManagementException.class)
    protected ResponseEntity<Object> handleError(UserManagementException userManagementException) {
        final ErrorResponse body = userManagementException.getErrorResponse();
        final HttpStatus httpStatus = userManagementException.getStatusCode();
        return new ResponseEntity(body, httpStatus);
    }

}
