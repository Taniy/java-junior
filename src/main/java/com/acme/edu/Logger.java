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
    private static  int countString = 0;
    private static String lastString = "";
    private static boolean sumExist = false;
    //endregion

    private Logger() {
    }

    /**
     * Log for byte message
     * Print @param message type byte
     * example "primitive: 1"
     * where byte message = 1
     */
    public static void log(byte message) {
        checkOnInt();
        checkOnString();
        print(PRIMITIVE + message);
    }

    /**
     * Log for int message
     * Print @param message type int
     * example "primitive: 1"
     * where int message = 1.
     */
    public static void log(int message) {
        checkOnString();
        if(message == ZEROVALUE || message == Integer.MAX_VALUE || message == Integer.MIN_VALUE) {
            checkOnInt();
            print(PRIMITIVE + message);
        } else if (checkOnOverFlowMaxValue(message)||checkOnOverFlowMinValue(message)) {
            print(PRIMITIVE + sumInt);
            sumInt = message;
            sumExist = true;
        } else {
            sumInt += message;
            sumExist = true;
        }
    }

    /**
     * Log for boolean message
     * Print @param message type boolean
     * example "primitive: true"
     * where boolean message = true
     */
    public static void log(boolean message) {
        checkOnInt();
        checkOnString();
        print(PRIMITIVE + message);
    }

    /**
     * Log for char message
     * Print @param message type char
     * example "char: m"
     * where m - char message
     */
    public static void log(char message) {
        checkOnInt();
        checkOnString();
        print("char: " + message);
    }

    /**
     * Log for string message
     * Print @param message type String
     * if prev string message = current  message,
     * print "string: str (xn)",
     * where n = times of repeat string,
     * "str"- string message
     * if n = 1 print "string: str"
     */
    public static void log(String message) {
        checkOnInt();
        if (message == lastString)
            countString++;
        else {
            checkOnString();
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
        checkOnInt();
        checkOnString();
        print("reference: " + message);
    }

    /**
     * Log for sum of array message
     * Print @param message type array
     * example "primitives array: 7"
     * where message = {1,3,3}
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
     * Print @param message type matrix
     * example "primitive matrix: {{1,3},{2,3}}
     * where message = {{1,3},{2,3}}
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
     * Print @param message type multiArray
     * example "primitives multimatrix: {{{{0}}}}"
     * where message {{{{0}}}}
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
     * Log for string vararg
     * Print @param message type string vararg
     * print concat of strings
     * example "str strings str 2"
     * where message = {"str", "strings", "str 2"}
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
     * need to write close() at the end of using class
     * print all saved messages, that haven't logged yet
     */
    public static void close() {
        checkOnInt();
        checkOnString();
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static void  checkOnString() {
        if (countString == SUMEMPTY)
            return;
        String str = "string: " + lastString;
        if (countString == 1) {
            print(str);
        } else {
            print(str + " (x" + countString + ")");
        }
        countString = SUMEMPTY;
    }

    private static void  checkOnInt() {
        if (!sumExist)
            return;
        print(PRIMITIVE + sumInt);
        sumInt = SUMEMPTY;
        sumExist = false;
    }

    private static boolean checkOnOverFlowMaxValue(int  num) {
        boolean flag = false;
        if ((sumExist) && (sumInt > 0) && (num > 0) && (sumInt > Integer.MAX_VALUE - num))
            flag = true;
        return flag;
    }

    private static boolean checkOnOverFlowMinValue(int num) {
        boolean flag = false;
        if ((sumExist) && (num < 0) && (sumInt < 0) && (sumInt < Integer.MIN_VALUE + num))
            flag = true;
        return flag;
    }

    private static void putInString(int[] ints, StringBuilder str) {
        for (int j = 0; j< ints.length; j++) {
            str.append(ints[j]);
            if (j != ints.length - 1)
                str.append(", ");
        }
    }
}
