package com.lexora.exception;

import org.springframework.http.HttpStatus;

/**
 * Base application exception for Lexora.
 */
public class LexoraException extends RuntimeException {

    private final HttpStatus status;

    public LexoraException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public LexoraException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
