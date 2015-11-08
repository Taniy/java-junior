package com.acme.edu.exceptions;

/**
 * LogException for Logger
 */
public class LogException extends Exception {
    /**
     * Constructor with empty param
     */
    public LogException() {
        super();
    }

    /**
     * constructor LogException for message
     * @param message
     */
    public LogException(String message) {
        super(message);
    }

    /**
     * constructor LogException for message and cause
     * @param message
     * @param cause
     */
    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor LogException for message
     * @param cause
     */
    public LogException(Throwable cause) {
        super(cause);
    }
}
