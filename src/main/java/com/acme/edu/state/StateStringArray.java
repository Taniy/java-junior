package com.acme.edu.state;

import com.acme.edu.exceptions.StateException;
import com.acme.edu.printer.Printer;

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
    public void log(String message) throws StateException {
        String[] strings;
        strings = message.split(", ");
        String str = "";
        for (int i = 0; i < strings.length;  i++) {
            str = str + SEP + strings[i];
        }
        str = str.substring(2,str.length()-1);
        print(str, printers);
    }


    @Override
    public void flush() {
    }
}
