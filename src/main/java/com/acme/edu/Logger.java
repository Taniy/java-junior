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
    public static final String BRAKETOPEN = "{" + SEP;
    public static final String BRAKETCLOSE = "}" + SEP;
    public static final int ZEROVALUE = 0;
    public static final int SUMEMPTY = 0;
    private static int sumInt = 0;
    private static  int sumString = 0;
    private static String lastString = "";
    //endregion

    private Logger() {
    }

    /**
     * Log for byte message
     * Print @param message
     */
    public static void log(byte message) {
        checkOnInt();
        checkOnString();
        print(PRIMITIVE + message);
    }

    /**
     * Log for int message
     * Print @param message
     */
    public static void log(int message) {
        checkOnString();
        if(message == ZEROVALUE || message == Integer.MAX_VALUE) {
            checkOnInt();
            print(PRIMITIVE + message);
        } else if ((sumInt != SUMEMPTY) && (message > 0) && (sumInt > Integer.MAX_VALUE - message)) {
            print(PRIMITIVE + sumInt);
            print(PRIMITIVE + message);
        } else {
            sumInt = message + sumInt;
        }
    }

    /**
     * Log for boolean message
     * Print @param message
     */
    public static void log(boolean message) {
        checkOnInt();
        checkOnString();
        print(PRIMITIVE + message);
    }

    /**
     * Log for char message
     * Print @param message
     */
    public static void log(char message) {
        checkOnInt();
        checkOnString();
        print("char: " + message);
    }

    /**
     * Log for string message
     * Print @param message
     */
    public static void log(String message) {
        checkOnInt();
        if (message == lastString)
            sumString++;
        else {
            checkOnString();
            lastString = message;
            sumString = 1;
        }
    }

    /**
     * Log for object message
     * Print @param message
     */
    public static void log(Object message) {
        checkOnInt();
        checkOnString();
        print("reference: " + message);
    }

    /**
     * Log for array message
     * Print @param message
     */
    public static void log(int... message) {
        int sum = SUMEMPTY;
        for (int i = 0; i < message.length;  i++) {
            sum = sum + message[i];
        }
        print("primitives array: " + sum);
    }

    /**
     * Log for integerMatrix
     * Print @param message
     */
    public static void log(int[][] message) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < message.length;  i++) {
            str.append("{");
            putInString(message[i], str);
            str.append(BRAKETCLOSE);
        }
        print("primitives matrix: " + BRAKETOPEN + str.toString() + "}");

    }

    /**
     * Log for multiArray
     * Print @param message
     */
    public static void log(int[][][][] message) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < message.length;  i++) {
            str.append(BRAKETOPEN);
            for (int j = 0; j < message[i].length;  j++) {
                str.append(BRAKETOPEN);
                for (int k = 0; k < message[i][j].length;  k++) {
                    str.append(BRAKETOPEN);
                    putInString(message[i][j][k], str);
                    str.append(SEP + BRAKETCLOSE);
                }
                str.append(BRAKETCLOSE);
            }
            str.append(BRAKETCLOSE);
        }
        print("primitives multimatrix: " + BRAKETOPEN + str.toString() + "}");

    }

    /**
     * Log for vararg
     * Print @param message
     */
    public static void log(String... message) {
        String str = "";
        for (int i = 0; i < message.length;  i++) {
            str = str+ SEP + message[i];
        }
        print(str);
    }

    /**
     * finish actions of logger
     * write close() at the end of using class
     */
    public static void close() {
        checkOnInt();
        checkOnString();
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static void  checkOnString() {
        if (sumString == SUMEMPTY)
            return;
        String str = "string: " + lastString;
        if (sumString == 1) {
            print(str);
        } else {
            print(str + " (x" + sumString + ")");
        }
        sumString = SUMEMPTY;
    }

    private static void  checkOnInt() {
        if (sumInt == SUMEMPTY)
            return;
        print(PRIMITIVE + sumInt);
        sumInt = SUMEMPTY;
    }

    private static void putInString(int[] ints, StringBuilder str) {
        for (int j = 0; j< ints.length; j++) {
            str.append(ints[j]);
            if (j != ints.length - 1)
                str.append(", ");
        }
    }
}
