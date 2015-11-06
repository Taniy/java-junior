package com.acme.edu.printer;

import com.acme.edu.PrinterException;

/**
 * Interface of Printer
 */
public interface Printer {

    /**
     * print messages
     * @param message string
     */
    void print(String message) throws PrinterException;

}
