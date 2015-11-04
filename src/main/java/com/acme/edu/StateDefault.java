package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateDefault extends State {

    @Override
    public void log(String message) {
        printer.print(message);
    }

    @Override
    public State switchToIntState() {
        flush();
        return new StateInt();
    }

    @Override
    public State switchToStringState() {
        flush();
        return new StateString();
    }

    @Override
    public State switchToStringArrayState() {
        flush();
        return new StateStringArray();
    }

    @Override
    public State switchToDefaultState() {
        return this;
    }

    public void flush() {
    }
}