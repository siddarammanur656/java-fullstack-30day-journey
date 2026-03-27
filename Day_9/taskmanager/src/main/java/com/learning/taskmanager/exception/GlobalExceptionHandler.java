package com.learning.taskmanager.exception;

import com.learning.taskmanager.dto.ApiResponse;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation
        .MethodArgumentTypeMismatchException;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation errors from @Valid 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>>
    handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult()
          .getFieldErrors()
          .forEach((FieldError e) ->
              fieldErrors.put(e.getField(), e.getDefaultMessage())
          );

        return ResponseEntity
            .badRequest()
            .body(ApiResponse.error(
                "Validation failed: " + fieldErrors, "VALIDATION_ERROR"
            ));
    }

    // Resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>>
    handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.error(ex.getMessage(), "NOT_FOUND"));
    }

    // Bad argument
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>>
    handleBadArg(IllegalArgumentException ex) {
        return ResponseEntity
            .badRequest()
            .body(ApiResponse.error(ex.getMessage(), "BAD_REQUEST"));
    }

    // State conflict
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<Void>>
    handleBadState(IllegalStateException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ApiResponse.error(ex.getMessage(), "CONFLICT"));
    }

    // Wrong type for path variable
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>>
    handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String msg = "Parameter '" + ex.getName() + "' has wrong type";
        return ResponseEntity
            .badRequest()
            .body(ApiResponse.error(msg, "TYPE_MISMATCH"));
    }

    // Catch-all safety net
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>>
    handleAll(Exception ex) {
        System.err.println("Unhandled exception: " + ex.getMessage());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.error(
                "Unexpected error. Please try again.",
                "INTERNAL_ERROR"
            ));
    }
}