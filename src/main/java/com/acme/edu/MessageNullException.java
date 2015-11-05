package com.acme.edu;

/**
 * Created by tan on 05.11.15.
 */
public class MessageNullException extends Exception {
    public MessageNullException() {
        super();
    }

    public MessageNullException(String message) {
        super(message);
    }

    public MessageNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageNullException(Throwable cause) {
        super(cause);
    }
}
