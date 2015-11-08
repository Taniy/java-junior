package com.acme.edu.exceptions;

import java.util.ArrayList;

/**
 * PrinterException class
 */
public class PrinterException extends LogException {

    /**
     * constructor PrinterException
     * no param
     */
    public PrinterException() {
        super();
    }

    /**
     * constructor for string message
     * @param message string
     */
    public PrinterException(String message) {
        super(message);
    }

    /**
     * constructor PrinterException for message and cause
     * @param message string
     * @param cause Throwable
     */
    public PrinterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor PrinterException for cause
     * @param cause Throwable
     */
    public PrinterException(Throwable cause) {
        super(cause);
    }
}
