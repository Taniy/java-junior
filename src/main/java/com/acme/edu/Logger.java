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
    public static final String BRAKETCLOSE = "}"+ SEP;
    private static Integer sumInt = null;
    private static  int countString = 0;
    private static String lastString = "";
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
        if(message == 0 || message == Integer.MAX_VALUE || message == Integer.MIN_VALUE) {
            checkOnInt();
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
                putInString(message[i][j], str);
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
        if (countString == 0)
            return;
        String str = "string: " + lastString;
        if (countString == 1) {
            print(str);
        } else {
            print(str + " (x" + countString + ")");
        }
        countString = 0;
    }

    private static void  checkOnInt() {
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
        for (int i = 0; i < ints.length; i++) {
            str.append("{");
            for (int j = 0; j < ints.length; j++) {
                str.append(ints[i][j]);
                if (j != ints.length - 1)
                    str.append(", ");
            }
            str.append(BRAKETCLOSE);
        }
    }
}
