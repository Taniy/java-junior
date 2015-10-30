package com.acme.edu;

public class Logger {
    //region fields
    public static final String PRIMITIVE = "primitive: ";
    public static int sumInt = 0;
    public static  int sumString = 0;
    public static String lastString = "";
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
     * print message
     * @param message
     */
    private static void printer(String message) {
        System.out.println(message);
    }

    /**
     * print sum of String that we have in buf
     * if sumString !=0
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
     * if  sumInt!=0
     */
    private static void  checkOnInt() {
        if (sumInt != 0) {
            printer(PRIMITIVE + sumInt);
            sumInt = 0;
        }
    }

    /**
     * close and print all what we have in buffer
     */
    public static void close() {
        checkOnInt();
        checkOnString();
    }
}
