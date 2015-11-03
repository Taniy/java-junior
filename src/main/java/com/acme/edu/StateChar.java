package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateChar extends State {
    public StateChar() {
        logSumOfStringsInBuf();
        logSumOfIntInBuf();
    }

    @Override
    public void realize(String message) {
        printer.print("char: " + message);
    }
}
