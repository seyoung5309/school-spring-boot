package com.mirim.board.dto;

import java.util.Map;

public class ApiErrorResponse {
    private final int status;
    private final String message;
    private final Map<String, String> errors;

    public ApiErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
