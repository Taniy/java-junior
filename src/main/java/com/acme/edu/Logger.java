package com.acme.edu;

/**
 * at the end of using Logger
 * you should call function close()
 */
public class Logger {
    //region fields
    public static final String PRIMITIVE = "primitive: ";
    private static int sumInt = 0;
    private static  int sumString = 0;
    private static String lastString = "";
    //endregion

    /**
     * log for byte message
     * @param message
     */
    public static void log(byte message) {
        checkOnInt();
        checkOnString();
        printer(PRIMITIVE + message);
    }

    /**
     * log for int message
     * @param message
     */
    public static void log(int message) {
        checkOnString();
        if(message == 0 || message == Integer.MAX_VALUE) {
            checkOnInt();
            printer(PRIMITIVE + message);
        } else {
            sumInt = message + sumInt;
        }
    }

    /**
     * log for boolean message
     * @param message
     */
    public static void log(boolean message) {
        checkOnInt();
        checkOnString();
        printer(PRIMITIVE + message);
    }

    /**
     * log for char message
     * @param message
     */
    public static void log(char message) {
        checkOnInt();
        checkOnString();
        printer("char: " + message);
    }

    /**
     * log for string message
     * @param message
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
     * log for object message
     * @param message
     */
    public static void log(Object message) {
        checkOnInt();
        checkOnString();
        printer("reference: " + message);
    }

    /**
     * log for array message
     * @param args
     */
    public static void log(int... args) {
        int sum = 0;
        for(int i = 0; i < args.length;  i++) {
            sum = sum + args[i];
        }
        printer("primitives array: " + sum);
    }

    /**
     * log for integerMatrix
     * @param args
     */
    public static void log(int[][] args) {
        String str = "";
        for(int i = 0; i < args.length;  i++) {
            str = str + "{";
            for (int j = 0; j<args[i].length; j++) {
                str = str + args[i][j];
                if (j != args[i].length - 1)
                    str = str + ", ";
            }
            str = str + "}" + System.lineSeparator();
        }
        printer("primitives matrix: " + "{" + System.lineSeparator() + str + "}");

    }

    /**
     * log for vararg
     * @param args
     */
    public static void log(String... args) {
        String str = "";
        for(int i = 0; i < args.length;  i++) {
            str = str+ System.lineSeparator() + args[i];
        }
        printer(str);
    }

    /**
     * close  at the end of using class
     */
    public static void close() {
        checkOnInt();
        checkOnString();
    }

    /**
     * print message
     * @param message
     */
    private static void printer(String message) {
        System.out.println(message);
    }

    /**
     * print sum of String that we have in buf
     */
    private static void  checkOnString() {
         if (sumString != 0) {
             if (sumString == 1)
                 printer("string: " +lastString);
             else
                 printer("string: " + lastString + " (x" + sumString + ")");
             sumString = 0;
         }
    }

    /**
     * print sum of int that we have in buf
     */
    private static void  checkOnInt() {
        if (sumInt != 0) {
            printer(PRIMITIVE + sumInt);
            sumInt = 0;
        }
    }
}
