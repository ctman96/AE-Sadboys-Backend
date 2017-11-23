package com.ipfms.exception;

/**
 * Exception to be thrown when an attempt to get an entity from
 * a repository returns empty.
 */
public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5597371741018039691L;
    public EntityNotFoundException(String message) {
        super(message);
    }
}