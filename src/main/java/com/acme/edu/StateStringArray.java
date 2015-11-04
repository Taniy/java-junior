package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateStringArray extends State {
    private static final String SEP = System.lineSeparator();

    @Override
    public void realize(String message) {
        String[] strings;
        strings = message.split(" ");
        String str = "";
        for (int i = 0; i < strings.length;  i++) {
            str = str + SEP + strings[i];
        }
        printer.print(str);
    }

    private void flush() {
    }

    @Override
    public State switchToIntState() {
        flush();
        state = new StateInt();
        return state;
    }

    @Override
    public State switchToStringState() {
        state = new StateString();
        return state;
    }

    @Override
    public State switchToCharState() {
        flush();
        state = new StateChar();
        return state;

    }

    @Override
    public State switchToStringArrayState() {
        return state;
    }

    @Override
    public State switchToBooleanState() {
        flush();
        state = new StateBoolean();
        return state;
    }

    @Override
    public State switchToReferenceState() {
        flush();
        state  = new StateReference();
        return state;
    }
}
