package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateBoolean extends State {

    @Override
    public void realize(String message) {
        printer.print(PRIMITIVE + message);
    }

    @Override
    public State switchToIntState() {
        flush();
        state = new StateInt();
        return state;
    }

    @Override
    public State switchToStringState() {
        flush();
        state = new StateString();
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
        logSumOfIntInBuf();
    }
}
