package com.acme.edu.state;

import com.acme.edu.IllegalArgumentException;
import com.acme.edu.printer.Printer;
import com.acme.edu.PrinterException;

/**
 * logging for StateDefault type
 * (char, boolean, reference, object)
 */
public class StateDefault implements State {
    private Printer[] printers;

    /**
     * pass printer to StateDefault
     * @param printers Printer
     */
    public StateDefault(Printer... printers) {
        this.printers = printers;
    }



    @Override
    public void log(String message) throws IllegalArgumentException {
        for(Printer printer: printers)
            try {
                printer.print(message);
            } catch (PrinterException e) {
                throw new IllegalArgumentException(e);
            }
    }

    @Override
    public void flush() {
    }
}