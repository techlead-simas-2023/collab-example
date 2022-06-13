package com.sekoding.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Represents an exception type for any data that is not found in the data
 * source.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends Exception {

    public DataNotFoundException(String message) {
        super(message);
    }
}
