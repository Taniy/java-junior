package com.acme.edu;

/**
 * at the end of using Logger
 * you should call function close()
 */
public class Logger {
    //region fields
    public static final String PRIMITIVE = "primitive: ";
    public static final String SEP = System.lineSeparator();
    public static final String BRAKETOPEN = "{" + SEP;
    public static final String BRAKETCLOSE = "}" + SEP;
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
        print(PRIMITIVE + message);
    }

    /**
     * log for int message
     * @param message
     */
    public static void log(int message) {
        checkOnString();
        if(message == 0 || message == Integer.MAX_VALUE) {
            checkOnInt();
            print(PRIMITIVE + message);
        } else {
            if ((sumInt != 0) && (message > 0) && (sumInt > Integer.MAX_VALUE - message)) {
                print(PRIMITIVE + sumInt);
                print(PRIMITIVE + message);
            } else {
                sumInt = message + sumInt;
            }
        }
    }

    /**
     * log for boolean message
     * @param message
     */
    public static void log(boolean message) {
        checkOnInt();
        checkOnString();
        print(PRIMITIVE + message);
    }

    /**
     * log for char message
     * @param message
     */
    public static void log(char message) {
        checkOnInt();
        checkOnString();
        print("char: " + message);
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
        print("reference: " + message);
    }

    /**
     * log for array message
     * @param message
     */
    public static void log(int... message) {
        int sum = 0;
        for(int i = 0; i < message.length;  i++) {
            sum = sum + message[i];
        }
        print("primitives array: " + sum);
    }

    /**
     * log for integerMatrix
     * @param message
     */
    public static void log(int[][] message) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < message.length;  i++) {
            str.append("{");
            getString(message[i], str);
            str.append(BRAKETCLOSE);
        }
        print("primitives matrix: " + BRAKETOPEN + str.toString() + "}");

    }

    /**
     * log for multiArray
     * @param message
     */
    public static void log(int[][][][] message) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < message.length;  i++) {
            str.append(BRAKETOPEN);
            for(int j = 0; j < message[i].length;  j++) {
                str.append(BRAKETOPEN);
                for(int k = 0; k < message[i][j].length;  k++) {
                    str.append(BRAKETOPEN);
                    getString(message[i][j][k], str);
                    str.append(SEP + BRAKETCLOSE);
                }
                str.append(BRAKETCLOSE);
            }
            str.append(BRAKETCLOSE);
        }
        print("primitives multimatrix: " + BRAKETOPEN + str.toString() + "}");

    }

    /**
     * log for vararg
     * @param message
     */
    public static void log(String... message) {
        String str = "";
        for(int i = 0; i < message.length;  i++) {
            str = str+ SEP + message[i];
        }
        print(str);
    }


    /**
     * close
     * at the end of using class
     */
    public static void close() {
        checkOnInt();
        checkOnString();
    }

    /**
     * print message
     * @param message
     */
    private static void print(String message) {
        System.out.println(message);
    }

    /**
     * print sum of String that we have in buf
     */
    private static void  checkOnString() {
         if (sumString != 0) {
             if (sumString == 1)
                 print("string: " + lastString);
             else
                 print("string: " + lastString + " (x" + sumString + ")");
             sumString = 0;
         }
    }

    /**
     * print sum of int that we have in buf
     */
    private static void  checkOnInt() {
        if (sumInt != 0) {
            print(PRIMITIVE + sumInt);
            sumInt = 0;
        }
    }

    private static void getString( int[] ints,StringBuilder str) {
        for (int j = 0; j< ints.length; j++) {
            str.append(ints[j]);
            if (j != ints.length - 1)
                str.append(", ");
        }
    }
}
