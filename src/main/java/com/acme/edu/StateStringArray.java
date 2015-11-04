package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateStringArray extends State {
    private static final String SEP = System.lineSeparator();

    @Override
    public void log(String message) {
        String[] strings;
        strings = message.split(", ");
        String str = "";
        for (int i = 0; i < strings.length;  i++) {
            str = str + SEP + strings[i];
        }
        str = str.substring(2,str.length()-1);
        printer.print(str);
    }

    public void flush() {
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
        return this;
    }

    @Override
    public State switchToDefaultState() {
        flush();
        return new StateDefault();
    }
}
