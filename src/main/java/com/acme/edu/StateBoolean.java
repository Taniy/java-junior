package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateBoolean extends State {

    public StateBoolean() {
        logSumOfIntInBuf();
        logSumOfStringsInBuf();
    }

    @Override
    public void realize(String message) {
        printer.print(PRIMITIVE + message);
    }
}
