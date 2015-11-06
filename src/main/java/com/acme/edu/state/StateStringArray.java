package com.acme.edu.state;

import com.acme.edu.IllegalArgumentException;
import com.acme.edu.printer.Printer;
import com.acme.edu.PrinterException;

/**
 *Class StateString Array implements State
 * logging for type StringArray
 */
public class StateStringArray implements State {
    private static final String SEP = System.lineSeparator();
    private Printer[] printers;

    /**
     * pass printer to StateStringArray
     * @param printers Printer
     */
    public StateStringArray(Printer... printers) {
        this.printers = printers;
    }

    @Override
    public void log(String message) throws IllegalArgumentException {
        String[] strings;
        strings = message.split(", ");
        String str = "";
        for (int i = 0; i < strings.length;  i++) {
            str = str + SEP + strings[i];
        }
        str = str.substring(2,str.length()-1);
        printToPrinter(str);
    }

    private void printToPrinter(String message) throws IllegalArgumentException {
        for(Printer printer: printers)
            try {
                printer.print(message);
            } catch (PrinterException e) {
                throw  new IllegalArgumentException(e);
            }
    }


    @Override
    public void flush() {
    }
}
