package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateString extends State {

    @Override
    public void realize(String message) {
        if (message == lastString)
            countString++;
        else {
            logSumOfStringsInBuf();
            lastString = message;
            countString = 1;
        }
    }

    @Override
    public State switchToIntState() {
        flush();
        state = new StateInt();
        return state;
    }

    @Override
    public State switchToStringState() {
        return state;
    }

    @Override
    public State switchToStringArrayState() {
        flush();
        state = new StateStringArray();
        return state;
    }

    @Override
    public State switchToBooleanState() {
        flush();
        state = new StateBoolean();
        return state;
    }

    @Override
    public State switchToCharState() {
        flush();
        state = new StateChar();
        return state;
    }

    @Override
    public State switchToReferenceState() {
        flush();
        state = new StateReference();
        return state;
    }

    private void flush() {
        logSumOfStringsInBuf();
    }
}