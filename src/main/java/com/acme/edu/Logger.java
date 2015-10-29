package com.acme.edu;

public class Logger {
    public static final String PRIMITIVE = "primitive: ";
//region fields

//endregion

    /**
     * print for int and byte message
     * @param message
     */
    public static void log(int message) {
        printer(PRIMITIVE + message);
    }

    /**
     * print for boolean message
     * @param message
     */
    public static void log(boolean message) {
        printer(PRIMITIVE + message);
    }

    /**
     * print for char message
     * @param message
     */
    public static void log(char message) {
        printer("char: " + message);
    }

    /**
     * print for string message
     * @param message
     */
    public static void log(String message) {
        printer("string: " + message);
    }

    /**
     * print for object message
     * @param message
     */
    public static void log(Object message) {
        printer("reference: " + message);
    }

    /**
     * print message
     * @param message
     */
    public static void printer(String message) {
        System.out.println(message);
    }
}
