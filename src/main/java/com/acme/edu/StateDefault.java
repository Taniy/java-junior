package com.acme.edu;

/**
 * logging for StateDefault type
 * (char, boolean, reference, object)
 */
public class StateDefault implements State {
    private Printer printer;

    /**
     * pass printer to StateDefault
     * @param printer Printer
     */
    public StateDefault(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void log(String message) throws PrinterException {
        printer.print(message);
    }

    @Override
    public void flush() {
    }
}