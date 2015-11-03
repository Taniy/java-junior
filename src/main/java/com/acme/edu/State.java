package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public abstract class State {
    protected static  int countString;
    protected static String lastString = "";
    protected static Integer sumInt = null;
    public static final int EMPTY_SUM_OF_STRINGS = 0;
    public static final String PRIMITIVE = "primitive: ";
    public static final int SUM_OF_END_MESSAGE = 0;
    Printer printer = new ConsolePrinter();

    public abstract void realize(String message);

    protected void logSumOfStringsInBuf() {
        if (countString == EMPTY_SUM_OF_STRINGS)
            return;
        String str = "string: " + lastString;
        if (countString == 1) {
            printer.print(str);
        } else {
            printer.print(str + " (x" + countString + ")");
        }
        countString = EMPTY_SUM_OF_STRINGS;
    }

    protected void logSumOfIntInBuf() {
        if (sumInt == null)
            return;
        printer.print(PRIMITIVE + sumInt);
        sumInt = null;
    }

    public void close() {
        logSumOfIntInBuf();
        logSumOfStringsInBuf();
    }
}
