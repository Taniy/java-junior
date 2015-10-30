package com.acme.edu;

public class Logger {
    //region fields
    public static final String PRIMITIVE = "primitive: ";
    public static int last = 0;
    public static  boolean flag = false;
    //endregion


    public static void log(byte message) {
        if (flag) {
            printer(PRIMITIVE + last);
        }
        printer(PRIMITIVE + message);
    }

    /**
     * log for int and byte message
     * @param message
     */
    public static void log(int message) {
        if(message == 0) {
            if (flag == true)
                printer(PRIMITIVE +last);
            printer(PRIMITIVE + message);
        } else {
            flag = true;
            last = message + last;
        }
    }

    /**
     * log for boolean message
     * @param message
     */
    public static void log(boolean message) {
        if (flag) {
            printer(PRIMITIVE + last);
        }
        printer(PRIMITIVE + message);
    }

    /**
     * log for char message
     * @param message
     */
    public static void log(char message) {
        if (flag) {
            printer(PRIMITIVE + last);
        }
        printer("char: " + message);
    }

    /**
     * log for string message
     * @param message
     */
    public static void log(String message) {
        if (flag) {
            printer(PRIMITIVE + last);
        }
        printer("string: " + message);
    }

    /**
     * log for object message
     * @param message
     */
    public static void log(Object message) {
        if (flag) {
            printer(PRIMITIVE + last);
        }
        printer("reference: " + message);
    }

    /**
     * print message
     * @param message
     */
    public static void printer(String message) {
        System.out.println(message);
        flag = false;
    }
}
