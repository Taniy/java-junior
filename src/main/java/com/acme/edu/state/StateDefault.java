package com.acme.edu.state;

import com.acme.edu.exceptions.StateException;
import com.acme.edu.printer.Printer;

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
    public void log(String message) throws StateException {
        print(message, printers);
    }

    @Override
    public void flush() {
    }
}