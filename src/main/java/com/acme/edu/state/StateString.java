package com.acme.edu.state;

import com.acme.edu.exceptions.StateException;
import com.acme.edu.printer.Printer;

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
    public void log(String message) throws StateException {
        if (message.equals(lastString))
            countString++;
        else {
            flush();
            lastString = message;
            countString = 1;
        }
    }

    @Override
    public void flush() throws StateException {
        if (countString == EMPTY_SUM_OF_STRINGS)
            return;
        String str = "string: " + lastString;
        if (countString == 1) {
            print(str, printers);
        } else {
            print(str + " (x" + countString + ")", printers);
        }
        countString = EMPTY_SUM_OF_STRINGS;
    }
}