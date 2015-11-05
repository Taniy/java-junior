package com.acme.edu;

/**
 * Created by tan on 03.11.15.
 */
public class ConsolePrinter implements Printer {
    @Override
    public void print(String message) throws PrinterException {
        if (message == null)
            throw new PrinterException(message);
        System.out.println(message);
    }
}
