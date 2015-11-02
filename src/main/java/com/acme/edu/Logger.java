package com.acme.edu;

/**
 * Log messages.
 * At the end of using Logger
 * you should call function close().
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
    //endregion

    /**
     * constructor of Logger
     */
    public Logger() {
    }

    enum State {
        CONTAINS_STRINGS,CONTAINS_INT, CONTAINS_INT_AND_STRINGS
    }

    /**
     * Log for byte message
     * Print @param message type byte
     * example "primitive: 1"
     * where byte message = 1
     */
    public static void log(byte message) {
        containInBuf(State.CONTAINS_INT_AND_STRINGS);
        print(PRIMITIVE + message);
    }

    /**
     * Log for int message
     * Print @param message type int
     * example "primitive: 1"
     * where int message = 1.
     */
    public static void log(int message) {
        containInBuf(State.CONTAINS_STRINGS);
        if(message == SUM_OF_END_MESSAGE || message == Integer.MAX_VALUE || message == Integer.MIN_VALUE) {
            logSumOfIntInBuf();
            print(PRIMITIVE + message);
        } else if ((sumInt != null) && (checkOnOverFlowMaxValue(message)||checkOnOverFlowMinValue(message))) {
            print(PRIMITIVE + sumInt);
            sumInt = message;
        } else  {
            if (sumInt == null) {
                sumInt = 0;
            }
            sumInt =  sumInt + message;
        }
    }

    /**
     * Log for boolean message
     * Print @param message type boolean
     * example "primitive: true"
     * where boolean message = true
     */
    public static void log(boolean message) {
        containInBuf(State.CONTAINS_INT_AND_STRINGS);
        print(PRIMITIVE + message);
    }

    /**
     * Log for char message
     * Print @param message type char
     * example "char: m"
     * where m - char message
     */
    public static void log(char message) {
        containInBuf(State.CONTAINS_INT_AND_STRINGS);
        print("char: " + message);
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
    public static void log(String message) {
        logSumOfIntInBuf();
        if (message == lastString)
            countString++;
        else {
            logSumOfStringsInBuf();
            lastString = message;
            countString = 1;
        }
    }

    /**
     * Log for object message
     * Print @param message type Object
     * example "reference: @"
     * where message = @
     */
    public static void log(Object message) {
        containInBuf(State.CONTAINS_INT_AND_STRINGS);
        print("reference: " + message);
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
    public static void log(String... message) {
        String str = "";
        for (int i = 0; i < message.length;  i++) {
            str = str + SEP + message[i];
        }
        print(str);
    }

    /**
     * finish actions of logger
     * need to write close() at the end of using class
     * containInBuf all saved messages, that haven't logged yet
     */
    public static void close() {
        containInBuf(State.CONTAINS_INT_AND_STRINGS);
    }

    private static void containInBuf(State state) {
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

    private static boolean checkOnOverFlowMaxValue(int  num) {
        boolean flag = false;
        if ((sumInt > 0) && (num > 0) && (sumInt > Integer.MAX_VALUE - num))
            flag = true;
        return flag;
    }

    private static boolean checkOnOverFlowMinValue(int num) {
        boolean flag = false;
        if ((num < 0) && (sumInt < 0) && (sumInt < Integer.MIN_VALUE - num))
            flag = true;
        return flag;
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
