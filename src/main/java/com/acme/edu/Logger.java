package com.acme.edu;

import java.util.Arrays;

/**
 * Log messages.
 * At the end of using Logger
 * you should call function finish().
 */
public class Logger {
    //region fields
    public static final String PRIMITIVE = "primitive: ";
    public static final String CHAR = "char: ";
    public static final String REFERENCE = "reference: ";
    public static final String PRIMITIVES_ARRAY = "primitives array: ";
    public static final String PRIMITIVES_MATRIX = "primitives matrix: ";
    public static final String PRIMITIVES_MULTIMATRIX = "primitives multimatrix: ";
    public static final String SEP = System.lineSeparator();
    public static final String BRACE_OPEN = "{" + SEP;
    public static final String BRACE_CLOSE = "}"+ SEP;
    private Printer printer = new ConsolePrinter();
    private State state = new StateDefault(printer);
    //endregion

    /**
     * constructor of Logger
     */
    public Logger() {
    }

    /**
     * Log for int message
     * Print @param message type int
     * example "primitive: 1"
     * where int message = 1.
     */
    public void log(int message) throws PrinterException {
        state = state.switchToState(new StateInt(printer));
        state.log("" + message);
    }

    /**
     * Log for boolean message
     * Print @param message type boolean
     * example "primitive: true"
     * where boolean message = true
     */
    public void log(boolean message) throws PrinterException {
        state = state.switchToState(new StateDefault(printer));
        state.log(PRIMITIVE + message);
    }

    /**
     * Log for char message
     * Print @param message type char
     * example "char: m"
     * where m - char message
     */
    public void log(char message) throws PrinterException {
        state = state.switchToState(new StateDefault(printer));
        state.log(CHAR + message);
    }

    /**
     * Log for string message
     * Print @param message type String
     * if prev string message = current  message,
     * containInBuf "string: str (xn)",
     * where n = times of repeat string,
     * "str"- string message
     * if n = 1 containInBuf "string: str"
     */
    public void log(String message) throws MessageNullException, PrinterException {
            checkOnNull(message);
            state = state.switchToState(new StateString(printer));
            state.log(message);
    }

    /**
     * Log for object message
     * Print @param message type Object
     * example "reference: @"
     * where message = @
     */
    public void log(Object message) throws MessageNullException, PrinterException {
            checkOnNull(message);
            state = state.switchToState(new StateDefault(printer));
            state.log(REFERENCE + message);

    }

    /**
     * Log for sum of array message
     * Print @param message type array
     * example "primitives array: 7"
     * where message = {1,3,3}
     */
    public static void log(int... message) {
        int sum = 0;
        for (int i = 0; i < message.length;  i++) {
            sum = sum + message[i];
        }
        print(PRIMITIVES_ARRAY + sum);
    }

    /**
     * Log for integerMatrix
     * Print @param message type matrix
     * example "primitive matrix: {{1,3},{2,3}}
     * where message = {{1,3},{2,3}}
     */
    public static void log(int[][] message) {
        StringBuilder str = new StringBuilder();
        putInString(message, str);
        print(PRIMITIVES_MATRIX + BRACE_OPEN + str.toString() + "}");
    }

    /**
     * Log for multiArray
     * Print @param message type multiArray
     * example "primitives multimatrix: {{{{0}}}}"
     * where message {{{{0}}}}
     */
    public static void log(int[][][][] message) {
        StringBuilder str = new StringBuilder();
        for (int[][][] matrixThree: message) {
            str.append(BRACE_OPEN);
            for (int [][] matrix: matrixThree) {
                str.append(BRACE_OPEN);
                putInString(matrix, str);
                str.append(BRACE_CLOSE);
            }
            str.append(BRACE_CLOSE);
        }
        print(PRIMITIVES_MULTIMATRIX + BRACE_OPEN + str.toString() + "}");

    }

    /**
     * Log for string vararg
     * Print @param message type string vararg
     * containInBuf concat of strings
     * example "str strings str 2"
     * where message = {"str", "strings", "str 2"}
     */
    public void log(String... message) throws MessageNullException, PrinterException {
            checkOnNull(message);
            state = state.switchToState(new StateStringArray(printer));
            state.log(Arrays.toString(message));
        }
    /**
     * finish actions of logger
     * need to write finish() at the end of using class
     * containInBuf all saved messages, that haven't logged yet
     */
    public void finish() throws PrinterException {
        state.flush();
    }

    private  static  void print(String message) {
        System.out.println(message);
    }

    private static void putInString(int[][] ints, StringBuilder str) {
        for (int[] array: ints) {
            str.append("{");
            for (int j = 0; j < ints.length; j++) {
                str.append(array[j]);
                if (j != ints.length - 1)
                    str.append(", ");
            }
            str.append(BRACE_CLOSE);
        }
    }

    private void checkOnNull(Object message) throws MessageNullException {
        if (message == null)
            throw new MessageNullException("" + message);
    }
}
