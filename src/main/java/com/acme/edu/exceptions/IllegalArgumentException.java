package com.acme.edu.exceptions;

/**
 * IllegalArgumentException extends Exception
 * for wrong parameters
 */
public class IllegalArgumentException extends LogException {

    /**
     * constructor IllegalArgumentException with empty param
     */
    public IllegalArgumentException() {
        super();
    }

    /**
     * constructor IllegalArgumentException fot message
     * @param message
     */
    public IllegalArgumentException(String message) {
        super(message);
    }

    /**
     * constructor IllegalArgumentException fot message and cause
     * @param message
     * @param cause
     */
    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor IllegalArgumentException fot cause
     * @param cause
     */
    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
