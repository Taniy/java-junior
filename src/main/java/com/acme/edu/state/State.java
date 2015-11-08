package com.acme.edu.state;

import com.acme.edu.exceptions.PrinterException;
import com.acme.edu.exceptions.StateException;
import com.acme.edu.printer.Printer;

import java.util.ArrayList;

/**
 * class for different types of State
 */
public interface State {
    /**
     * logging string messages
     * @param message string
     */
    void log(String message) throws StateException;

    /**
     * change to current state
     * @return state
     */
    default State switchToState(State state) throws StateException {
        if (!(state.getClass() == this.getClass())){
            this.flush();
            return state;
        }
        return this;
    }

    /**
     * print message to all passed printers
     * @param message
     * @param printers
     * @throws StateException (list of all print exceptions)
     */
    default void print(String message, Printer... printers) throws StateException {
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

    /**
     * clean all messages saved in buffer
     */
    void flush() throws StateException;

}
