package com.acme.edu;

/**
 * Class MessageNullException extends Exception
 */
public class MessageNullException extends Exception {
    /**
     * constructor MessageNullException with empty param
     */
    public MessageNullException() {
        super();
    }

    /**
     * constructor MessageNullException for string message
     * @param message string
     */
    public MessageNullException(String message) {
        super(message);
    }

    /**
     * constructor MessageNullException fot message and cause
     * @param message string
     * @param cause Throwable
     */
    public MessageNullException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor MessageNullException for cause
     * @param cause Throwable
     */
    public MessageNullException(Throwable cause) {
        super(cause);
    }
}
