package com.acme.edu.printer;

import com.acme.edu.exceptions.PrinterException;

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
        System.out.println(message);
    }
}
