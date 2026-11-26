package com.mirim.board.exception;

import com.mirim.board.dto.ApiErrorResponse;
import org.hibernate.query.sqm.PathElementException;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(PathElementException.class)
    public ResponseEntity<ApiErrorResponse> handleBadSort(PathElementException e) {
        return handleApiException(new ApiException(HttpStatus.BAD_REQUEST,
                "정렬 기준이 올바르지 않습니다."));
    }
}
