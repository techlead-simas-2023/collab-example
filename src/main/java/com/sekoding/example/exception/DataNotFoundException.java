package com.sekoding.example.exception;

/**
 * Represents an exception type for any data that is not found in the data
 * source.
 */
public class DataNotFoundException extends Exception {

    public DataNotFoundException(String message) {
        super(message);
    }
}
