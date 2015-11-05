package com.acme.edu;

/**
 * IllegalArgumentException extends Exception
 */
public class IllegalArgumentException extends Exception {

    /**
     * constructor MessageNullException with empty param
     */
    public IllegalArgumentException() {
        super();
    }

    /**
     * constructor printerexception fot message
     * @param message
     */
    public IllegalArgumentException(String message) {
        super(message);
    }

    /**
     * constructor printerexception fot message and cause
     * @param message
     * @param cause
     */
    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor printerexception fot cause
     * @param cause
     */
    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
