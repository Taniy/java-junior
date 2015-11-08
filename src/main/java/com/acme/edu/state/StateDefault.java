package com.acme.edu.state;

import com.acme.edu.exceptions.IllegalArgumentException;
import com.acme.edu.exceptions.StateException;
import com.acme.edu.printer.Printer;
import com.acme.edu.exceptions.PrinterException;

import java.util.ArrayList;

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
        ArrayList<Exception> list = new ArrayList<>();
        for(Printer printer: printers) {
            try {
                printer.print(message);
            } catch (PrinterException e) {
                list.add(e);
            }
        }
        if (!list.isEmpty())
            throw new StateException(list.toString());
    }

    @Override
    public void flush() {
    }
}