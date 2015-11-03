package com.acme.edu;

/**
 * Log messages.
 * At the end of using Logger
 * you should call function finish().
 */
public class Logger {
    //region fields
    public static final String PRIMITIVE = "primitive: ";
    public static final String SEP = System.lineSeparator();
    public static final String BRACE_OPEN = "{" + SEP;
    public static final String BRACE_CLOSE = "}"+ SEP;
    public static final int SUM_OF_END_MESSAGE = 0;
    public static final int EMPTY_SUM_OF_STRINGS = 0;
    private static Integer sumInt = null;
    private static  int countString;
    private static String lastString = "";
    private State state;
    //endregion

    /**
     * constructor of Logger
     */
    public Logger() {
    }

    enum Stat {
        CONTAINS_STRINGS,CONTAINS_INT, CONTAINS_INT_AND_STRINGS
    }

    /**
     * Log for int message
     * Print @param message type int
     * example "primitive: 1"
     * where int message = 1.
     */
    public void log(int message) {
        state = new StateInt();
        state.realize(String.valueOf(message));
    }

    /**
     * Log for boolean message
     * Print @param message type boolean
     * example "primitive: true"
     * where boolean message = true
     */
    public void log(boolean message) {
        state = new StateBoolean();
        state.realize(String.valueOf(message));
    }

    /**
     * Log for char message
     * Print @param message type char
     * example "char: m"
     * where m - char message
     */
    public void log(char message) {
        state = new StateChar();
        state.realize(String.valueOf(message));
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
    public void log(String message) {
        state = new StateString();
        state.realize(message);
    }

    /**
     * Log for object message
     * Print @param message type Object
     * example "reference: @"
     * where message = @
     */
    public void log(Object message) {
        state = new StateReference();
        state.realize(String.valueOf(message));
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
        print("primitives array: " + sum);
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
        print("primitives matrix: " + BRACE_OPEN + str.toString() + "}");
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
        print("primitives multimatrix: " + BRACE_OPEN + str.toString() + "}");

    }

    /**
     * Log for string vararg
     * Print @param message type string vararg
     * containInBuf concat of strings
     * example "str strings str 2"
     * where message = {"str", "strings", "str 2"}
     */
    public void log(String... message) {
        state = new StateStringArray();
        state.realize(message.toString());
    }

    /**
     * finish actions of logger
     * need to write finish() at the end of using class
     * containInBuf all saved messages, that haven't logged yet
     */
    public void finish() {
        state.close();
    }

    private static void containInBuf(Stat state) {
        switch (state) {
            case CONTAINS_INT_AND_STRINGS:
                logSumOfStringsInBuf();
                logSumOfIntInBuf();
                break;
            case CONTAINS_INT:
                logSumOfIntInBuf();
                break;
            case CONTAINS_STRINGS:
                logSumOfStringsInBuf();
                break;
        }
    }

    private  static  void print(String message) {
        System.out.println(message);
    }

    private static void logSumOfStringsInBuf() {
        if (countString == EMPTY_SUM_OF_STRINGS)
            return;
        String str = "string: " + lastString;
        if (countString == 1) {
            print(str);
        } else {
            print(str + " (x" + countString + ")");
        }
        countString = EMPTY_SUM_OF_STRINGS;
    }

    private static void logSumOfIntInBuf() {
        if (sumInt == null)
            return;
        print(PRIMITIVE + sumInt);
        sumInt = null;
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
}
