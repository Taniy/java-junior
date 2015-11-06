package com.acme.edu.printer;

import com.acme.edu.PrinterException;

/**
 * Class ConsolePrinter implements Printer
 */
public class ConsolePrinter implements Printer {
    /**
     * print to console
     * @param message string
     * @throws PrinterException
     */
    @Override
    public void print(String message) throws PrinterException {
        if (message == null)
            throw new PrinterException(message);
        System.out.println(message);
    }
}
