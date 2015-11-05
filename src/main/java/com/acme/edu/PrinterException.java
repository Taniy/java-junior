package com.acme.edu;

/**
 * Created by tan on 05.11.15.
 */
public class PrinterException extends Exception {
    public PrinterException() {
        super();
    }

    public PrinterException(String message) {
        super(message);
    }

    public PrinterException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrinterException(Throwable cause) {
        super(cause);
    }

    protected PrinterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
