package com.mirim.board.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private final HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
