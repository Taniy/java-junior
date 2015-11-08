package com.acme.edu.state;

import com.acme.edu.exceptions.IllegalArgumentException;
import com.acme.edu.printer.Printer;
import com.acme.edu.exceptions.PrinterException;

import java.util.ArrayList;

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
        ArrayList<Exception> list = new ArrayList<>();
        for(Printer printer: printers) {
            try {
                printer.print(message);
            } catch (PrinterException e) {
                list.add(e);
            }
        }
        if (!list.isEmpty())
            throw new IllegalArgumentException(list.toString());
    }


    @Override
    public void flush() {
    }
}
