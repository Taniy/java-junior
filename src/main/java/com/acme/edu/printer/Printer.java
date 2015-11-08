package com.acme.edu.printer;

import com.acme.edu.exceptions.PrinterException;

/**
 * Interface of Printer
 */
public interface Printer {
    PrinterException printerException = new PrinterException();

    /**
     * print messages
     * @param message string
     */
    void print(String message) throws PrinterException;

}
