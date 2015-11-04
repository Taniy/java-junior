package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public abstract class State {
    protected Printer printer  = new ConsolePrinter();

    public abstract void log(String message);
    public abstract State switchToIntState();
    public abstract State switchToStringState();
    public abstract State switchToStringArrayState();
    public abstract State switchToDefaultState();
    public abstract void flush();

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

}
