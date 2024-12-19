package com.grupo5.tareas.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException customException) {
        Map<String, String> body = Map.of("message", customException.getMessage());
        return ResponseEntity.status(customException.getStatus()).body(body);
    }
}
