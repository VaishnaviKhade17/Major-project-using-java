package com.example.gradetracker.student_grade_tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception used when a requested resource (like a Student) is not found.
 * * @ResponseStatus(value = HttpStatus.NOT_FOUND) ensures Spring Boot automatically
 * returns an HTTP 404 status code when this exception is thrown.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    // Default constructor
    public ResourceNotFoundException() {
        super();
    }
    
    // Constructor to pass a custom message (the one used in the Service layer)
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    // Constructor to handle wrapping another exception
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}