package com.acme.edu;

public class Logger {
//region fields

//endregion




    public static void log(int message) {
        printer("primitive: " + message);
    }

    public static void log(byte message) {
        printer("primitive: " + message);
    }

    public static void log(boolean message) {
        printer("primitive: " + message);
    }

    public static void log(char message) {
        printer("char: " + message);
    }

    public static void log(String message) {
        printer("string: "+message);
    }

    public static void printer(String message) {
        System.out.println(message);
    }
}
