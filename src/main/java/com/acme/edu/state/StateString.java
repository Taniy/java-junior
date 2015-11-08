package com.acme.edu.state;

import com.acme.edu.exceptions.IllegalArgumentException;
import com.acme.edu.printer.Printer;
import com.acme.edu.exceptions.PrinterException;

/**
 * StateString implements State
 * logging for type String
 */
public class StateString implements State {
    private static final int EMPTY_SUM_OF_STRINGS = 0;
    private int countString;
    private String lastString = "";
    private Printer[] printers;

    /**
     * constructor StateString
     * @param printers Printer
     */
    public StateString(Printer... printers) {
        this.printers = printers;
    }

    /**
     * count sum repeated strings
     * and then print them
     * @param message string
     */
    @Override
    public void log(String message) throws IllegalArgumentException {
        if (message.equals(lastString))
            countString++;
        else {
            flush();
            lastString = message;
            countString = 1;
        }
    }

    @Override
    public void flush() throws IllegalArgumentException {
        if (countString == EMPTY_SUM_OF_STRINGS)
            return;
        String str = "string: " + lastString;
        if (countString == 1) {
            printToPrinter(str);
        } else {
            printToPrinter(str + " (x" + countString + ")");
        }
        countString = EMPTY_SUM_OF_STRINGS;
    }

    private void printToPrinter(String message) throws IllegalArgumentException {
        for(Printer printer: printers)
            try {
                printer.print(message);
            } catch (PrinterException e) {
                throw new IllegalArgumentException(e);
            }
    }
}