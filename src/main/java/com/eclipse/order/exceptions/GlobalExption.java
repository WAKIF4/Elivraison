package com.eclipse.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExption {
    
    @ExceptionHandler(NotFoundExcption.class)
    public ResponseEntity<String> notFound(NotFoundExcption e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"" + e.getMessage() + "\"}");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)-> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            System.out.println("Error: " + fieldName + " " + errorMessage);
            errors.put(fieldName,errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
