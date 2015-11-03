package com.acme.edu;

import java.lang.reflect.Array;

/**
 * Created by tan on 02.11.15.
 */
public class StateStringArray extends State {
    private static final String SEP = System.lineSeparator();

    public StateStringArray() {
        logSumOfStringsInBuf();
        logSumOfIntInBuf();
    }

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
}
