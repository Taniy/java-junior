package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateReference extends State {

    public StateReference() {
        logSumOfIntInBuf();
        logSumOfStringsInBuf();
    }

    @Override
    public void realize(String message) {
        printer.print("reference: " + message);
    }
}
