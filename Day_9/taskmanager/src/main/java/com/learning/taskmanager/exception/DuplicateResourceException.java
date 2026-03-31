package com.learning.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a unique constraint would be violated (username/email duplicate).
 * Returns 409 CONFLICT to the client.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String resource, String field, Object value) {
        super(resource + " already exists with " + field + ": " + value);
    }

    public DuplicateResourceException(String message) {
        super(message);
    }
}
