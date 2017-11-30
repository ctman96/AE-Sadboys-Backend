package com.ipfms.exception;

/**
 * Exception to be thrown when a user is not permitted to be using a function
 */
public class UnauthorizedCallException extends RuntimeException  {
    private static final long serialVersionUID = 5632082700939079374L;
    public UnauthorizedCallException(String message) {
        super(message);
    }
}
