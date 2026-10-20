package com.mirim.board.exception;

import com.mirim.board.dto.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException e) {
        ApiErrorResponse body = new ApiErrorResponse(e.getStatus().value(), e.getMessage(), Map.of());
        return ResponseEntity.status(e.getStatus()).body(body);
    }

}
