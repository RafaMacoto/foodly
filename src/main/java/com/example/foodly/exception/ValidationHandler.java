package com.example.foodly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    record ValidationError(String field, String message) {
        public ValidationError(FieldError e) {
            this(e.getField(), e.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationError> handleValidationException(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .toList();
    }
}
