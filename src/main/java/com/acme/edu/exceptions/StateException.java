package com.acme.edu.exceptions;

/**
 * Class StateException extends LogException
 * logging state class
 */
public class StateException extends LogException {

    /**
     * constructor StateException for empty params
     */
    public StateException() {
        super();
    }

    /**
     * constructor StateException for message
     * @param message string
     */
    public StateException(String message) {
        super(message);
    }

    /**
     * constructor StateException for message and cause
     * @param message string
     * @param cause Throwable
     */
    public StateException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor StateException for cause
     * @param cause Throwable
     */
    public StateException(Throwable cause) {
        super(cause);
    }
}
