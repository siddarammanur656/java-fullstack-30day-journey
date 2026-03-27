package com.learning.taskmanager.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiResponse<T> {

    private boolean success;
    private String  message;
    private T       data;
    private String  timestamp;
    private String  error;
    private String  errorCode;

    private ApiResponse() {
        this.timestamp = LocalDateTime.now()
            .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = true;
        r.message = message;
        r.data    = data;
        return r;
    }

    public static <T> ApiResponse<T> success(String message) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = true;
        r.message = message;
        return r;
    }

    public static <T> ApiResponse<T> error(String error) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = false;
        r.error   = error;
        return r;
    }

    public static <T> ApiResponse<T> error(String error, String code) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success   = false;
        r.error     = error;
        r.errorCode = code;
        return r;
    }

    // Getters
    public boolean isSuccess()    { return success; }
    public String  getMessage()   { return message; }
    public T       getData()      { return data; }
    public String  getTimestamp() { return timestamp; }
    public String  getError()     { return error; }
    public String  getErrorCode() { return errorCode; }
}