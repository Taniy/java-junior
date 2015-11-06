package com.acme.edu;

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
     * constructor LogException fot message
     * @param message
     */
    public LogException(String message) {
        super(message);
    }

    /**
     * constructor LogException fot message and cause
     * @param message
     * @param cause
     */
    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor LogException fot message
     * @param cause
     */
    public LogException(Throwable cause) {
        super(cause);
    }
}
