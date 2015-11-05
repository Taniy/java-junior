package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateString implements State {
    private static final int EMPTY_SUM_OF_STRINGS = 0;
    private int countString;
    private String lastString = "";
    private Printer printer;

    /**
     * constructor StateString
     * @param printer Printer
     */
    public StateString(Printer printer) {
        this.printer = printer;
    }

    /**
     * count sum repeated strings
     * and then print them
     * @param message string
     */
    @Override
    public void log(String message) throws PrinterException {
        if (message.equals(lastString))
            countString++;
        else {
            flush();
            lastString = message;
            countString = 1;
        }
    }

    @Override
    public void flush() throws PrinterException {
        if (countString == EMPTY_SUM_OF_STRINGS)
            return;
        String str = "string: " + lastString;
        if (countString == 1) {
            printer.print(str);
        } else {
            printer.print(str + " (x" + countString + ")");
        }
        countString = EMPTY_SUM_OF_STRINGS;
    }
}