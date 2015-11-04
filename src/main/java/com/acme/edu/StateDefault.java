package com.acme.edu;

/**
 * logging for StateDefault type
 * (char, boolean, reference, object)
 */
public class StateDefault extends State {
    private Printer printer;

    /**
     * pass printer to StateDefault
     * @param printer Printer
     */
    public StateDefault(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void log(String message) {
        printer.print(message);
    }

    @Override
    public State switchToIntState() {
        return new StateInt(printer);
    }

    @Override
    public State switchToStringState() {
        return new StateString(printer);
    }

    @Override
    public State switchToStringArrayState() {
        return new StateStringArray(printer);
    }

    @Override
    public State switchToDefaultState() {
        return this;
    }

    @Override
    public void flush() {
    }
}