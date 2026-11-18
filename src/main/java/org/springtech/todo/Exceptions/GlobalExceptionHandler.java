package org.springtech.todo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> mapResponseEntity(
            HttpStatus status, String message)
    {
        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put("Code",status.value());
        responseBody.put("error",status.getReasonPhrase());
        responseBody.put("message",message);
        responseBody.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException exception
    ){
        return mapResponseEntity(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException exception
    ){
        Map<String, Object> validationErrors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    validationErrors.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );
                });

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("code",HttpStatus.BAD_REQUEST);
        body.put("error",HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("messages",validationErrors);
        body.put("timestamp",LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
