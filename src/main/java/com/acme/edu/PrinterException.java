package com.acme.edu;

/**
 * PrinterException class
 */
public class PrinterException extends Exception {
    /**
     * constructor printerexception
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
     * constructor printerexception fot message and cause
     * @param message string
     * @param cause Throwable
     */
    public PrinterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor printerexception fot cause
     * @param cause Throwable
     */
    public PrinterException(Throwable cause) {
        super(cause);
    }
}
