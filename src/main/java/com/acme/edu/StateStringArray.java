package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateStringArray extends State {
    private static final String SEP = System.lineSeparator();
    private Printer printer;

    /**
     * pass printer to StateStringArray
     * @param printer Printer
     */
    public StateStringArray(Printer printer) {
        this.printer = printer;
    }

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

    @Override
    public void flush() {
    }

    @Override
    public State switchToIntState() {
        return new StateInt(printer);
    }

    @Override
    public State switchToStringState() {
        return new StateString(printer);
    }

    @Override
    public State switchToStringArrayState() {
        return this;
    }

    @Override
    public State switchToDefaultState() {
        return new StateDefault(printer);
    }
}
