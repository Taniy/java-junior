package com.acme.edu.exceptions;

/**
 *  ServerException extends Exception
 *  for server exceptions
 */
public class ServerException extends Exception {
    /**
     * constructor ServerException for empty param
     */
    public ServerException() {
        super();
    }

    /**
     * constructor ServerException for message
     * @param message string
     */
    public ServerException(String message) {
        super(message);
    }

    /**
     * constructor ServerException for message and cause
     * @param message string
     * @param cause Throwable
     */
    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor ServerException for and cause
     * @param cause Throwable
     */
    public ServerException(Throwable cause) {
        super(cause);
    }
}
