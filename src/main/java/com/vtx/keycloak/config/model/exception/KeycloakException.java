package com.vtx.keycloak.config.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter

public class KeycloakException extends RuntimeException {
    private HttpStatus status;

    public KeycloakException(HttpStatus status) {
        this.status = status;
    }

    public KeycloakException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public KeycloakException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public KeycloakException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public KeycloakException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
