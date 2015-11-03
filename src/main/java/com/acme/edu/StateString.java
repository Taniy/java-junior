package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateString extends State {

    public StateString() {
        logSumOfIntInBuf();
    }

    @Override
    public void realize(String message) {
        logSumOfIntInBuf();
        if (message == lastString)
            countString++;
        else {
            logSumOfStringsInBuf();
            lastString = message;
            countString = 1;
        }
    }
}
